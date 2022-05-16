package com.dalmope.persona.service;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImageMongoService {

    List<ImageDTO> getImages();

    ImageDTO getImage(String id);

    List<ImageDTO> getImagesByPerson(Long id);

    ImageDTO saveImage(ImageDTO image, Long personID);

    ImageDTO updateImage(String id, ImageDTO image, Long personID);

    ResponseEntity<Response> deleteImage(String id);

}
