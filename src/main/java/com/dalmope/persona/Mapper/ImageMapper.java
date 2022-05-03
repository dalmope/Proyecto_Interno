package com.dalmope.persona.Mapper;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.Model.MYSQL.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "image", target = "image")

    ImageDTO toDTO(Image image);
    List<ImageDTO> map(List<Image> images);

}
