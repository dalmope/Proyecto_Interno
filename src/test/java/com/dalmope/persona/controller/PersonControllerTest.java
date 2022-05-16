package com.dalmope.persona.controller;

import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.Datos;
import com.dalmope.persona.controller.mysql.PersonController;
import com.dalmope.persona.exceptions.ObjectNotFoundException;
import com.dalmope.persona.model.mysql.Person;
import com.dalmope.persona.service.ImageMongoService;
import com.dalmope.persona.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageMongoService imageMongoService;

    @MockBean
    private PersonService personService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getPersons() throws Exception {
        //Given
        when(personService.getPersons()).thenReturn(Datos.PERSONDTO_LIST_IMAGE_NULL);
        //When
        assertEquals(Datos.PERSONDTO_LIST_IMAGE_NULL, personService.getPersons());
        doThrow(ObjectNotFoundException.class).when(personService.getPerson(null));
        //Then
        mockMvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getPersonsInAllDB() throws Exception {
        //Given
        when(personService.getPersonsInAllDB()).thenReturn(Datos.PERSONDTO_LIST_IMAGE_NULL);
        when(imageMongoService.getImagesByPerson(anyLong())).thenReturn(Datos.IMAGESDTO_LIST);

        //When
        assertEquals(Datos.PERSONDTO_LIST_IMAGE_NULL, personService.getPersons());
        //Then
        mockMvc.perform(get("/person/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Dalmope"))
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    void getPerson() throws Exception {
        //Given
        when(personService.getPerson(5L)).thenReturn(null);
        when(personService.getPerson(1L)).thenReturn(Datos.PERSONADTO_1);
        when(imageMongoService.getImagesByPerson(anyLong())).thenReturn(Datos.IMAGESDTO_LIST);

        //When
        assertEquals(Datos.PERSONADTO_1, personService.getPerson(1L));

        //Then
        mockMvc.perform(get("/person/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        //Then
        mockMvc.perform(get("/person/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPerson() throws Exception {
        //Given
        Person newPerson = new Person(1L, "Dalmope", "dalmope@dalmope.test", "12312", null);
        PersonDTO newPersonDTO = new PersonDTO(1L, "Dalmope", "dalmope@dalmope.test", null);

        when(personService.savePerson(newPerson)).thenReturn(newPersonDTO);
        when(personService.getPersonByNameOrEmail("Dalmope","dalmope@dalmope.test")).thenReturn(null);
        PersonDTO duplicatePerson = new PersonDTO(1L, "Alberto", "alberto@alberto.test", null);
        when(personService.getPersonByNameOrEmail("Alberto","alberto@alberto.test")).thenReturn((duplicatePerson));

        //Then
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPerson)))
                .andExpect(status().isCreated());

        //Then
        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicatePerson)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePerson() throws Exception {
        PersonDTO newPerson = new PersonDTO(1L, "Dalmope", "dalmope@dalmope.test", null);
        PersonDTO existPerson = new PersonDTO(2L, "Alberto", "alberto@alberto.test", null);
        PersonDTO duplicatePerson = new PersonDTO(3L, "David", "David@alberto.test", null);

        when(personService.getPersonByNameOrEmail("Dalmope","dalmope@dalmope.test")).thenReturn(null);
        when(personService.getPersonByNameOrEmail("Alberto","alberto@alberto.test")).thenReturn(existPerson);

        when(personService.getPerson(1L)).thenReturn(null);
        when(personService.getPerson(2L)).thenReturn(existPerson);

        //Then
        mockMvc.perform(put("/person/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(existPerson)))
                .andExpect(status().isNotFound());

        //Then
        mockMvc.perform(put("/person/2").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPerson)))
                .andExpect(status().isOk());

        when(personService.getPersonByNameOrEmail(anyString(),anyString())).thenReturn(duplicatePerson);
        mockMvc.perform(put("/person/3").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(duplicatePerson)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePerson() throws Exception {
        //Given
        PersonDTO newPerson = new PersonDTO(1L, "Dalmope", "dalmope@dalmope.test", null);

        when(personService.getPerson(1L)).thenReturn(newPerson);
        when(personService.getPerson(2L)).thenReturn(null);

        mockMvc.perform(delete("/person/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/person/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}