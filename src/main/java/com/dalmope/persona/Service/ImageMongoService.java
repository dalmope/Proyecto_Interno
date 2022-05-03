package com.dalmope.persona.Service;

import com.dalmope.persona.Model.MONGO.ImageMongo;
import com.dalmope.persona.Repository.MONGO.ImageMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageMongoService {

    @Autowired
    private ImageMongoRepository imageMongoRepository;

    public List<ImageMongo> getImages() {
        return imageMongoRepository.findAll();
    }

    public ImageMongo saveImage(ImageMongo imageMongo) {
        return imageMongoRepository.save(imageMongo);
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
