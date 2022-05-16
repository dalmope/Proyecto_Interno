package com.dalmope.persona.mapper;

import com.dalmope.persona.DTO.ImageDTO;

import com.dalmope.persona.model.mysql.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "data", target = "data")

    ImageDTO toDTO(Image image);
    List<ImageDTO> map(List<Image> images);

    Image toEntity(ImageDTO image);
}
