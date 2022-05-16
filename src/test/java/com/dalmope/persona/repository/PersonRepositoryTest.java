package com.dalmope.persona.repository;

import com.dalmope.persona.Datos;
import com.dalmope.persona.model.mysql.Person;
import com.dalmope.persona.repository.mysql.PersonRepository;
import com.dalmope.persona.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    void updatePerson() {
        when(personRepository.save(any(Person.class))).thenReturn(Datos.PERSONA_1);
        assertEquals(Datos.PERSONA_1, personService.updatePerson(Datos.PERSONA_1));
        when(personRepository.save(any(Person.class))).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> personService.updatePerson(null));
    }

    @Test
    void findByNameOrEmail() {
        when(personRepository.findByNameOrEmail(anyString(), anyString())).thenReturn(Optional.of(Datos.PERSONA_1));
        assertEquals(Datos.PERSONA_1, personService.getPersonByNameOrEmail("Dalmope", "dalmope@dalmope.test").orElseThrow());
        when(personRepository.findByNameOrEmail(anyString(), anyString())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> personService.getPersonByNameOrEmail(null, null).orElseThrow());
    }

    @Test
    void savePerson() {
        when(personRepository.saveAndFlush(any(Person.class))).thenReturn(Datos.PERSONA_1);
        assertEquals(Datos.PERSONA_1, personService.savePerson(Datos.PERSONA_1));
        when(personRepository.saveAndFlush(any(Person.class))).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> personService.savePerson(null));
    }

    @Test
    void deletePerson() {
        personRepository.deleteById(1L);
        verify(personRepository, times(1)).deleteById(anyLong());
        doNothing().when(personRepository).deleteById(anyLong());
        personService.deletePerson(1L);
        doThrow(new IllegalArgumentException()).when(personRepository).deleteById(anyLong());
        assertThrows(RuntimeException.class, () -> personService.deletePerson(1L));
    }

    @Test
    void getPersonById() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(Datos.PERSONA_1));
        assertEquals(Datos.PERSONA_1, personService.getPersonById(1L).orElseThrow());
        when(personRepository.findById(anyLong())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> personService.getPersonById(1L));
    }

    @Test
    void getPersons() {
        when(personRepository.findAll()).thenReturn(Datos.PERSON_LIST_IMAGE_NULL);
        assertEquals(Datos.PERSON_LIST_IMAGE_NULL, personService.getPersons());
        assertNull(Datos.PERSON_LIST_IMAGE_NULL.get(0).getImages());
        when(personRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> personService.getPersons());
    }

}