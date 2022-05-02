package com.dalmope.persona.Service;

import com.dalmope.persona.Model.MONGO.ImageMongo;
import com.dalmope.persona.Repository.MONGO.ImageMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class ImageMongoService {

    @Autowired
    private ImageMongoRepository imageMongoRepository;

    public List<ImageMongo> getImages() {
        List<ImageMongo> images = imageMongoRepository.findAll();
        images.forEach(this::DecodeImage);
        return images;
    }

    public ImageMongo saveImage(ImageMongo imageMongo) {
        EncodeImage(imageMongo);
        return imageMongoRepository.save(imageMongo);
    }

    public ImageMongo EncodeImage(ImageMongo image) {
        image.setImage(Base64.getEncoder().encode(image.getImage()));
        return image;
    }

    public ImageMongo DecodeImage(ImageMongo image)  {
        image.setImage(Base64.getDecoder().decode(image.getImage()));
        return image;
    }


}
