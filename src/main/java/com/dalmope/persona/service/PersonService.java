package com.dalmope.persona.service;

import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.model.mysql.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getPersons();
    PersonDTO getPerson(Long id);
    List<PersonDTO> getPersonsInAllDB();
    PersonDTO getPersonByNameOrEmail(String name, String email);
    PersonDTO savePerson(Person person);
    PersonDTO updatePerson(Long id, Person person);
    ResponseEntity<Response> deletePerson(Long id);

}
