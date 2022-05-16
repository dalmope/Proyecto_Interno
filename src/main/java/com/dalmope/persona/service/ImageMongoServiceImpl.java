package com.dalmope.persona.service;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.Response;
import com.dalmope.persona.configuration.Messagges;
import com.dalmope.persona.exceptions.ObjectNotFoundException;
import com.dalmope.persona.mapper.ImageMongoMapper;
import com.dalmope.persona.model.mongo.ImageMongo;
import com.dalmope.persona.repository.mongo.ImageMongoRepository;
import com.dalmope.persona.repository.mysql.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ImageMongoServiceImpl implements ImageMongoService {

    private final ImageMongoRepository imageMongoRepository;

    private final PersonRepository personRepository;

    public ImageMongoServiceImpl(ImageMongoRepository imageMongoRepository, PersonRepository personRepository) {
        this.imageMongoRepository = imageMongoRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<ImageDTO> getImages() {
        return ImageMongoMapper.INSTANCE.map(imageMongoRepository.findAll());
    }

    @Override
    public ImageDTO getImage(String id) {
        Optional<ImageMongo> imageMongo = imageMongoRepository.findById(id);
        if (imageMongo.isEmpty()) {
            throw new ObjectNotFoundException(Messagges.IMAGE_NOT_FOUND.getDescription());
        }
        return ImageMongoMapper.INSTANCE.toDTO(imageMongo.get());
    }

    @Override
    public List<ImageDTO> getImagesByPerson(Long id) {
        return ImageMongoMapper.INSTANCE.map(imageMongoRepository.findByPersonId(id));
    }

    @Override
    public ImageDTO saveImage(ImageDTO image, Long personID) {
        if (personRepository.findById(personID).isEmpty()) {
            throw new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription());
        }
        return ImageMongoMapper.INSTANCE.toDTO(imageMongoRepository.save(new ImageMongo(image.getData(), personID)));
    }

    @Override
    public ImageDTO updateImage(String id, ImageDTO image, Long personID) {
        Optional<ImageMongo> imageMongo = imageMongoRepository.findById(id);

        if (personRepository.existsById(personID)) {
            throw new ObjectNotFoundException(Messagges.PERSON_NOT_FOUND.getDescription());
        }
        if (imageMongo.isEmpty()) {
            throw new ObjectNotFoundException(Messagges.IMAGE_NOT_FOUND.getDescription());
        }

        imageMongo.get().setData(image.getData());
        return ImageMongoMapper.INSTANCE.toDTO(imageMongoRepository.save(imageMongo.get()));
    }

    @Override
    public ResponseEntity<Response> deleteImage(String id) {
        if (imageMongoRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException(Messagges.IMAGE_NOT_FOUND.getDescription());
        }
        imageMongoRepository.deleteById(id);
        return new ResponseEntity<>(new  Response(Messagges.IMAGE_DELETED.getDescription()), HttpStatus.OK);
    }

}
