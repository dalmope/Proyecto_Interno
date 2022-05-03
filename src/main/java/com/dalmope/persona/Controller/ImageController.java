package com.dalmope.persona.Controller;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.Model.MYSQL.Image;
import com.dalmope.persona.Service.ImageService;
import com.dalmope.persona.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Image> getImages() {
        return imageService.getImages();
    }

    @GetMapping("/{id}")
    public Image getImage(@PathVariable("id") int id) {
        if (imageService.getImage(id).isEmpty()) {
            throw new RuntimeException(ErrorCode.IMAGE_NOT_FOUND.getDescription());
        }
        return imageService.DecodeImage(imageService.getImage(id).get());
    }

    @PostMapping
    public Image addImage(@ModelAttribute MultipartFile image, Long personID) throws IOException {
        if (personService.getPersonById(personID).isEmpty()){
            throw new RuntimeException(ErrorCode.PERSON_NOT_FOUND.getDescription());
        }

        return imageService.addImage(new Image(image.getBytes(), personID));
    }

    @DeleteMapping
    public void deleteImage(Image image) {
        imageService.deleteImage(image);
    }

}

