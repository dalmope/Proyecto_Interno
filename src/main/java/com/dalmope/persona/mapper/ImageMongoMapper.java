package com.dalmope.persona.mapper;

import com.dalmope.persona.DTO.ImageDTO;

import com.dalmope.persona.model.mongo.ImageMongo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMongoMapper {

    ImageMongoMapper INSTANCE = Mappers.getMapper(ImageMongoMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "data", target = "data")

    ImageDTO toDTO(ImageMongo image);
    List<ImageDTO> map(List<ImageMongo> images);
}
