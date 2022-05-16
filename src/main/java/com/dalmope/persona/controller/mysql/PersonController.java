package com.dalmope.persona.controller.mysql;

import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.model.mysql.Person;
import com.dalmope.persona.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * It's a REST controller that maps HTTP requests to methods that perform CRUD operations on a Person object
 */
@RestController
@RequestMapping ("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/all")
    public List<PersonDTO> getPersonsInAllDB() {
        return personService.getPersonsInAllDB();
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    public PersonDTO savePerson(@RequestBody Person person) {
       return personService.savePerson(person);
    }

    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable Long id, @RequestBody Person person) {
       return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}
