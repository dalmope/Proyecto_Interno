package com.dalmope.persona.controller.mongo;

import com.dalmope.persona.DTO.ImageDTO;


import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.service.ImageMongoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * It's a REST controller that exposes endpoints for saving, retrieving, and deleting images from MongoDB
 */
@RestController
@RequestMapping("/imageMongo")
public class ImageMongoController {

    private final ImageMongoService imageMongoService;

    public ImageMongoController(ImageMongoService imageMongoService) {
        this.imageMongoService = imageMongoService;
    }

    @PostMapping
    public ImageDTO saveImage(@ModelAttribute MultipartFile image, Long personID) throws IOException {
        return imageMongoService.saveImage(new ImageDTO(image.getBytes()), personID);
    }

    @GetMapping
    public List<ImageDTO> getAllImages() {
        return imageMongoService.getImages();
    }

    @GetMapping("/{id}")
    public ImageDTO getImageById(@PathVariable String id) {
        return imageMongoService.getImage(id);
    }

    @PutMapping("/{id}")
    public ImageDTO updateImage(@PathVariable String id, @ModelAttribute MultipartFile image, Long personID) throws IOException {
        return imageMongoService.updateImage(id, new ImageDTO(image.getBytes()), personID);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteImageById(@PathVariable String id) {
        return imageMongoService.deleteImage(id);
    }

}
