package com.dalmope.persona.Mapper;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.Model.MYSQL.Image;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper {
    Image fromDTO(ImageDTO imageDTO);
    ImageDTO ToDTO(Image image);
}
