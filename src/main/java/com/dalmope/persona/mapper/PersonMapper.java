package com.dalmope.persona.mapper;

import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.model.mysql.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "images", target = "images")

    PersonDTO toDTO(Person person);

    Person toEntity(PersonDTO personDTO);
    List<PersonDTO> map(List<Person> persons);

}
