package com.dalmope.persona.Service;

import com.dalmope.persona.Model.MYSQL.Image;
import com.dalmope.persona.Repository.MYSQL.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getImages() {
        List<Image> images = imageRepository.findAll();
        images.forEach(this::DecodeImage);
        return images;
    }

    public Optional<Image> getImage(Integer id){
        return imageRepository.findById(id);
    }

    public Image addImage(Image image) {
        image.setImage(EncodeImage(image).getImage());
        return imageRepository.save(image);
    }

    public Image EncodeImage(Image image) {
        image.setImage(Base64.getEncoder().encode(image.getImage()));
        return image;
    }

    public Image DecodeImage(Image image)  {
        image.setImage(Base64.getDecoder().decode(image.getImage()));
        return image;
    }

    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }
}
