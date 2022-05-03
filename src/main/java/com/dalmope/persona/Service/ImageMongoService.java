package com.dalmope.persona.Service;

import com.dalmope.persona.Model.MYSQL.MONGO.ImageMongo;
import com.dalmope.persona.Repository.MONGO.ImageMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

    public void EncodeImage(ImageMongo image) {
        image.setImage(Base64.getEncoder().encode(image.getImage()));
    }

    public void DecodeImage(ImageMongo image)  {
        image.setImage(Base64.getDecoder().decode(image.getImage()));
    }

    public Optional<ImageMongo> getImageMongoById(String id) {
        return imageMongoRepository.findById(id);
    }

    public List<ImageMongo> getImageMongoByPersonId(Long id){
        return imageMongoRepository.findByPersonId(id);
    }

    public void deleteImageMongoByPersonId(Long id){
        imageMongoRepository.deleteByPersonId(id);
    }

    public void deleteImageMongoById(String id) {
        imageMongoRepository.deleteById(id);
    }


}
