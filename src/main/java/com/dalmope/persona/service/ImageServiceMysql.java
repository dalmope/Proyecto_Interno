package com.dalmope.persona.service;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.configuration.Messagges;
import com.dalmope.persona.exceptions.ObjectNotFoundException;
import com.dalmope.persona.mapper.ImageMapper;
import com.dalmope.persona.model.mysql.Image;
import com.dalmope.persona.model.mysql.Person;
import com.dalmope.persona.repository.mysql.ImageRepository;
import com.dalmope.persona.repository.mysql.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceMysql implements ImageService {

    private final ImageRepository imageRepository;

    private final PersonRepository personRepository;

    public ImageServiceMysql(ImageRepository imageRepository, PersonRepository personRepository) {
        this.imageRepository = imageRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<ImageDTO> getImages() {
        return ImageMapper.INSTANCE.map(imageRepository.findAll());
    }

    @Override
    public ImageDTO getImage(Long id){
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            throw new ObjectNotFoundException(Messagges.IMAGE_NOT_FOUND.getDescription());
        }
        return ImageMapper.INSTANCE.toDTO(image.orElseThrow());
    }

    @Override
    public ImageDTO saveImage(ImageDTO image, Long personID) {
        if (personRepository.findById(personID).isEmpty()){
            throw new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription());
        }
        Person person = personRepository.findById(personID).orElseThrow();
        Image newImage = new Image(image.getData(), person);
        return ImageMapper.INSTANCE.toDTO(imageRepository.save(newImage));
    }

    @Override
    public ImageDTO updateImage(Long id, ImageDTO image, Long personID) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isEmpty()) {
            throw new ObjectNotFoundException(Messagges.IMAGE_NOT_FOUND.getDescription());
        }
        if (personRepository.findById(personID).isEmpty()){
            throw new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription());
        }
        imageOptional.get().setData(image.getData());
        return ImageMapper.INSTANCE.toDTO(imageRepository.save(imageOptional.get()));
    }

    @Override
    public ResponseEntity<Response> deleteImage(Long id) {
        if (imageRepository.findById(id).isEmpty()){
            throw new ObjectNotFoundException(Messagges.IMAGE_NOT_FOUND.getDescription());
        }
        imageRepository.deleteById(id);
        return ResponseEntity.ok(new Response(Messagges.IMAGE_DELETED.getDescription()));
    }

}

