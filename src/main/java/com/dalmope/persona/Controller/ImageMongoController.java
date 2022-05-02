package com.dalmope.persona.Controller;

import com.dalmope.persona.Model.MONGO.ImageMongo;
import com.dalmope.persona.Service.ImageMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageMongoController {

    @Autowired
    private ImageMongoService imageMongoService;

    public void saveImage(ImageMongo image) {
        imageMongoService.saveImage(image);
    }

}
