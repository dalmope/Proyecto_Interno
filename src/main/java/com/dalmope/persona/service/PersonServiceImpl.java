package com.dalmope.persona.service;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.configuration.Messagges;
import com.dalmope.persona.exceptions.DuplicateObjectException;
import com.dalmope.persona.exceptions.ObjectNotFoundException;
import com.dalmope.persona.mapper.ImageMongoMapper;
import com.dalmope.persona.mapper.PersonMapper;
import com.dalmope.persona.model.mysql.Person;
import com.dalmope.persona.repository.mongo.ImageMongoRepository;
import com.dalmope.persona.repository.mysql.PersonRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final ImageMongoRepository imageMongoRepository;

    public PersonServiceImpl(PersonRepository personRepository, ImageMongoRepository imageMongoRepository) {
        this.personRepository = personRepository;
        this.imageMongoRepository = imageMongoRepository;
    }

    @Override
    public List<PersonDTO> getPersons() {
        return PersonMapper.INSTANCE.map(personRepository.findAll());
    }

    @Override
    public PersonDTO getPerson(Long id) {
        return PersonMapper.INSTANCE.toDTO(personRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription())));
    }

    @Override
    public List<PersonDTO> getPersonsInAllDB() {
        List<PersonDTO> persons = PersonMapper.INSTANCE.map(personRepository.findAll());

        for (PersonDTO person : persons) {
            Collection<ImageDTO> images = ImageMongoMapper.INSTANCE.map(imageMongoRepository.findByPersonId(person.getId()));
            person.getImages().addAll(images);
        }

        return persons;
    }

    @Override
    public PersonDTO getPersonByNameOrEmail(String name, String email) {
        return PersonMapper.INSTANCE.toDTO(personRepository.findByNameOrEmail(name, email).orElseThrow(
                () -> new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription())));
    }

    @Override
    public PersonDTO savePerson(Person person) {

        if (personRepository.findByNameOrEmail(person.getName(), person.getEmail()).isPresent()) {
            throw new DuplicateObjectException(Messagges.DUPLICATE_USER.getDescription());
        }
        return PersonMapper.INSTANCE.toDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, Person person) {
        if (personRepository.findByNameOrEmail(person.getName(), person.getName()).isPresent()) {
            throw new DuplicateObjectException(Messagges.DUPLICATE_USER.getDescription());
        }
        if (personRepository.findById(id).isPresent()) {
            return PersonMapper.INSTANCE.toDTO(personRepository.save(person));
        }
        throw new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription());
    }

    @Override
    public ResponseEntity<Response> deletePerson(Long id) {
        if (personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
        }
        throw new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription());
    }
}
