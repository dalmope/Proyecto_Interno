package com.dalmope.persona.Service;

import com.dalmope.persona.Model.MYSQL.Person;
import com.dalmope.persona.Repository.MYSQL.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getPersons() {
        try {
            return personRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Person> getPersonById(Long id) {
        try {
            return personRepository.findById(id);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<Person> getPersonByNameOrEmail(String name, String email) {
        try {
            return personRepository.findByNameOrEmail(name, email);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Person savePerson(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletePerson(Long id) {
        try {
            personRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updatePerson(Person person) {
        try {
            personRepository.save(person);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
