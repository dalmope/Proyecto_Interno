package com.dalmope.persona.controller.mysql;

import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * It's a controller that handles requests to the /image endpoint
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<ImageDTO> getImages() {
        return imageService.getImages();
    }

    @GetMapping("/{id}")
    public ImageDTO getImage(@PathVariable("id") Long id) {
        return imageService.getImage(id);
    }

    @PostMapping
    public ImageDTO saveImage(@ModelAttribute MultipartFile image, Long personID) throws IOException {
        return imageService.saveImage(new ImageDTO(image.getBytes()), personID);
    }

    @PutMapping("/{id}")
    public ImageDTO updateImage(@PathVariable("id") Long id, @ModelAttribute MultipartFile image, Long personID) throws IOException {
        return imageService.updateImage(id, new ImageDTO(image.getBytes()), personID);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteImage(@PathVariable("id") Long id) {
        return imageService.deleteImage(id);
    }

}

