package com.dalmope.persona.service;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.Response;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    public List<ImageDTO> getImages();

    public ImageDTO getImage(Long id);

    public ImageDTO saveImage(ImageDTO image, Long personID) throws IOException;

    public ImageDTO updateImage(Long id, ImageDTO image, Long personID) throws IOException;

    public ResponseEntity<Response> deleteImage(Long id);



}
