package com.dalmope.persona.service;

import com.dalmope.persona.Datos;
import com.dalmope.persona.repository.mongo.ImageMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageMongoServiceImplTest {

    @Mock
    ImageMongoRepository imageMongoRepository;

    @InjectMocks
    ImageMongoServiceImpl imageMongoService;

    @Test
    void getImages() {
        when(imageMongoRepository.findAll()).thenReturn(Datos.IMAGES_MONGO_LIST);

        assertEquals(Datos.IMAGESDTO_LIST, imageMongoService.getImages());
        assertEquals(3, imageMongoService.getImages().size());
    }

    @Test
    void getImage() {
        when(imageMongoRepository.findById("1")).thenReturn(Optional.of(Datos.IMAGE_MONGO_1));

        assertEquals(Datos.IMAGE_DTO_1, imageMongoService.getImage("1"));
    }

    @Test
    void getImagesByPerson() {
    }

    @Test
    void saveImage() {
    }

    @Test
    void updateImage() {
    }

    @Test
    void deleteImage() {
    }
}