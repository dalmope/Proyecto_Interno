package com.dalmope.persona.Controller;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.Model.MYSQL.Person;
import com.dalmope.persona.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        if (personService.getPersonByNameOrEmail(person.getName(),person.getEmail()).isPresent()) {
            throw new RuntimeException(ErrorCode.DUPLICATE_USER.getDescription());
        }
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        if (personService.getPersonByNameOrEmail(person.getName(), person.getName()).isPresent()) {
            return new ResponseEntity<>(ErrorCode.DUPLICATE_USER, ErrorCode.DUPLICATE_USER.getCode());
        }

        if (personService.getPersonById(id).isPresent()) {
            personService.updatePerson(person);
            return new ResponseEntity<>("Person was successfully updated." , HttpStatus.OK);
        }
        return new ResponseEntity<>("Person doesn't  exist." , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        if (personService.getPersonById(id).isPresent()) {
            personService.deletePerson(id);
            return new ResponseEntity<>("Person was successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Person doesn't  exist." , HttpStatus.OK);
    }
}
