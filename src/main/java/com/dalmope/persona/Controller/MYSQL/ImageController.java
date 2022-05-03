package com.dalmope.persona.Controller.MYSQL;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.Mapper.ImageMapper;
import com.dalmope.persona.Model.MYSQL.Image;
import com.dalmope.persona.Service.ImageService;
import com.dalmope.persona.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ImageService imageService;

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<ImageDTO> getImages() {
        return ImageMapper.INSTANCE.map(imageService.getImages());
    }

    @GetMapping("/{id}")
    public ImageDTO getImage(@PathVariable("id") int id) {
        if (imageService.getImage(id).isEmpty()) {
            throw new RuntimeException(ErrorCode.IMAGE_NOT_FOUND.getDescription());
        }
        return ImageMapper.INSTANCE.toDTO(imageService.DecodeImage(imageService.getImage(id).get()));
    }

    @PostMapping
    public ImageDTO addImage(@ModelAttribute MultipartFile image, Long personID) throws IOException {
        if (personService.getPersonById(personID).isEmpty()){
            throw new RuntimeException(ErrorCode.PERSON_NOT_FOUND.getDescription());
        }

        return ImageMapper.INSTANCE.toDTO(imageService.addImage(new Image(image.getBytes(), personService.getPersonById(personID).get())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") int id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

}

