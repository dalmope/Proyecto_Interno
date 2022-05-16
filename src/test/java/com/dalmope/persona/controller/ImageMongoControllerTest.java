package com.dalmope.persona.controller;

import com.dalmope.persona.Datos;
import com.dalmope.persona.model.mongo.ImageMongo;
import com.dalmope.persona.repository.mongo.ImageMongoRepository;
import com.dalmope.persona.repository.mysql.PersonRepository;
import com.dalmope.persona.service.ImageMongoService;
import com.dalmope.persona.service.ImageMongoServiceImpl;
import com.dalmope.persona.service.PersonService;
import com.dalmope.persona.controller.mongo.ImageMongoController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImageMongoController.class)
class ImageMongoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageMongoRepository imageMongoRepository;

    @MockBean
    private PersonRepository personRepository;

    @InjectMocks
    private ImageMongoService imageMongoService;

    @Test
    void saveImage() throws Exception {
        MockMultipartFile jsonFile = new MockMultipartFile("image", "image", "application/json", "{\"image\": \"image\"}".getBytes());

        when(personRepository.findById(anyLong())).thenReturn(Optional.of(Datos.PERSON_LIST.get(0)));
        when(imageMongoRepository.save(any(ImageMongo.class))).thenReturn(Datos.IMAGE_MONGO_1);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/imageMongo").file(jsonFile).param("personId", "1"))
                .andExpect(status().isOk());

        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/imageMongo").file(jsonFile).param("personId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllImages() throws Exception {
        when(imageMongoRepository.findAll()).thenReturn(Datos.IMAGES_MONGO_LIST);

        mockMvc.perform(get("/imageMongo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    void getImageById() throws Exception {
        ImageMongo image = Datos.IMAGES_MONGO_LIST.get(0);

        when(imageMongoRepository.findByPersonId(1L)).thenReturn(Datos.IMAGES_MONGO_LIST);
        when(imageMongoRepository.findByPersonId(2L)).thenReturn(null);

        mockMvc.perform(get("/imageMongo/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"));

        mockMvc.perform(get("/imageMongo/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteImageById() throws Exception {
        when(imageMongoRepository.findByPersonId(anyLong())).thenReturn(null);
        mockMvc.perform(delete("/imageMongo/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        when(imageMongoRepository.findByPersonId(anyLong())).thenReturn(Datos.IMAGES_MONGO_LIST);
        mockMvc.perform(delete("/imageMongo/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}