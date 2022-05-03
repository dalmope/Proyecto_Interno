package com.dalmope.persona.Mapper;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.Model.MONGO.ImageMongo;
import com.dalmope.persona.Model.MYSQL.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "images", target = "images")

    PersonDTO toDTO(Person person);
    List<PersonDTO> map(List<Person> persons);

}
