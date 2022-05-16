package com.dalmope.persona.repository;

import com.dalmope.persona.Datos;
import com.dalmope.persona.repository.mysql.ImageRepository;
import com.dalmope.persona.service.ImageServiceMysql;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageRepositoryTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceMysql imageService;

    @Test
    void getImages() {
        when(imageRepository.findAll()).thenReturn(Datos.IMAGES_LIST);
        assertEquals(Datos.IMAGES_LIST, imageService.getImages());
        assertEquals(Datos.IMAGES_LIST.size(), imageService.getImages().size());
        verify(imageRepository, times(2)).findAll();
    }

    @Test
    void getImage() {
        when(imageRepository.findById(anyInt())).thenReturn(Optional.of(Datos.IMAGE_1));
        assertEquals(Datos.IMAGE_1, imageService.getImage(1).orElseThrow());
        verify(imageRepository, times(1)).findById(anyInt());
    }

    @Test
    void createImage() {
        when(imageRepository.save(any())).thenReturn(Datos.IMAGE_1);
        assertEquals(Datos.IMAGE_1, imageService.addImage(Datos.IMAGE_1));
        verify(imageRepository, times(1)).save(any());
    }

    @Test
    void deleteImage() {
        imageService.deleteImage(1);
        verify(imageRepository, times(1)).deleteById(anyInt());
    }

}