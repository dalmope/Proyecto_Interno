package com.dalmope.persona.Controller;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.Model.MYSQL.Image;
import com.dalmope.persona.Service.ImageService;
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
    public Image addImage(@ModelAttribute MultipartFile image) throws IOException {
        return imageService.addImage(new Image(image.getBytes()));
    }

    @DeleteMapping
    public void deleteImage(Image image) {
        imageService.deleteImage(image);
    }

}

