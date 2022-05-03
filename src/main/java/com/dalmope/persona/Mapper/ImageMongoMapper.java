package com.dalmope.persona.Mapper;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.Model.MONGO.ImageMongo;
import com.dalmope.persona.Model.MYSQL.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ImageMongoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "image", target = "image")

    ImageMongo toDTO(Image image);
    List<ImageDTO> map(List<ImageMongo> images);
}
