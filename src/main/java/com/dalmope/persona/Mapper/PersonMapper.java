package com.dalmope.persona.Mapper;

import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.Model.MYSQL.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonDTO personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTO personDTO);
}
