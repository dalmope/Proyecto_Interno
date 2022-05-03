package com.dalmope.persona.Controller.MONGO;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.Model.MYSQL.MONGO.ImageMongo;
import com.dalmope.persona.Service.ImageMongoService;
import com.dalmope.persona.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ImageMongo")
public class ImageMongoController {

    @Autowired
    private ImageMongoService imageMongoService;

    @Autowired
    private PersonService personService;

    @PostMapping
    public ImageMongo saveImage(@ModelAttribute MultipartFile image, Long personID) throws IOException {
        if (personService.getPersonById(personID).isEmpty()) {
           throw new RuntimeException(ErrorCode.PERSON_NOT_FOUND.getDescription());
        }
        return imageMongoService.saveImage(new ImageMongo(image.getBytes(), personID));
    }

    @GetMapping
    public List<ImageMongo> getAllImages() {
        return imageMongoService.getImages();
    }

    @GetMapping("/{id}")
    public ImageMongo getImageById(@PathVariable String id) {
        if (imageMongoService.getImageMongoById(id).isEmpty()) {
            throw new RuntimeException(ErrorCode.IMAGE_NOT_FOUND.getDescription());
        }
        return imageMongoService.getImageMongoById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteImageById(@PathVariable String id) {
        if (imageMongoService.getImageMongoById(id).isEmpty()) {
            throw new RuntimeException(ErrorCode.IMAGE_NOT_FOUND.getDescription());
        }
        imageMongoService.deleteImageMongoById(id);
    }

}
