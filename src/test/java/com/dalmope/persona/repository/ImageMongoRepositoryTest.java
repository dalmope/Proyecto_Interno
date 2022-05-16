package com.dalmope.persona.repository;

import com.dalmope.persona.Datos;
import com.dalmope.persona.model.mongo.ImageMongo;
import com.dalmope.persona.repository.mongo.ImageMongoRepository;
import com.dalmope.persona.service.ImageMongoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageMongoRepositoryTest {

        @Mock
        ImageMongoRepository imageMongoRepository;

        @InjectMocks
        ImageMongoServiceImpl imageMongoService;

        @Test
        void findByPersonId() {
            when(imageMongoService.getImages()).thenReturn(Datos.IMAGESDTO_LIST);

            assertEquals(Datos.IMAGESDTO_LIST, imageMongoService.getImages());
            assertEquals(3, imageMongoService.getImages().size());
        }

        @Test
        void deleteByPersonId() {
            imageMongoService.deleteImage("1");
            verify(imageMongoRepository, times(1)).deleteByPersonId(anyLong());
        }

        @Test
        void save() {
            when(imageMongoRepository.save(any())).thenReturn(Datos.IMAGE_MONGO_1);
            ImageMongo imageMongo = imageMongoService.saveImage(Datos.IMAGE_MONGO_1);
            assertNotNull(imageMongo.getId());
            assertEquals(Datos.IMAGE_MONGO_1.getPersonId(), imageMongo.getPersonId());
            assertEquals(Datos.IMAGE_MONGO_1.getData(), imageMongo.getData());
        }

        @Test
        void findById() {
            when(imageMongoRepository.findById(anyString())).thenReturn((Optional.of(Datos.IMAGE_MONGO_1)));
            ImageMongo imageMongo = imageMongoService.getImageMongoById("1").orElseThrow();
            assertNotNull(imageMongo.getId());
            assertEquals(Datos.IMAGE_MONGO_1.getPersonId(), imageMongo.getPersonId());
            assertEquals(Datos.IMAGE_MONGO_1.getData(), imageMongo.getData());
        }

        @Test
        void deleteById() {
            imageMongoService.deleteImageMongoById("1");
            verify(imageMongoRepository, times(1)).deleteById(anyString());
        }

        @Test
        void getAllImages() {
            when(imageMongoRepository.findAll()).thenReturn(Datos.IMAGES_MONGO_LIST);
            assertEquals(3,imageMongoService.getImages().size());
            verify(imageMongoRepository, times(1)).findAll();
        }

    }