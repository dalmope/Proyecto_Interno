package com.dalmope.persona.controller;

import com.dalmope.persona.Datos;
import com.dalmope.persona.controller.mysql.ImageController;
import com.dalmope.persona.model.mysql.Image;
import com.dalmope.persona.repository.mysql.ImageRepository;
import com.dalmope.persona.repository.mysql.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImageController.class)
class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private PersonRepository personRepository;

    @Test
    void getImages() throws Exception {
        when(imageRepository.findAll()).thenReturn(Datos.IMAGES_LIST);

        mockMvc.perform(get("/image").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getImage() throws Exception {
        Image image = Datos.IMAGES_LIST.get(0);

        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(imageRepository.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/image/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));

        mockMvc.perform(get("/image/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void addImage() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("image", "image", "application/json", "{\"image\": \"image\"}".getBytes());

        when(personRepository.findById(any())).thenReturn(Optional.of(Datos.PERSON_LIST.get(0)));
        when(imageRepository.save(any(Image.class))).thenReturn(Datos.IMAGES_LIST.get(0));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/image").file(jsonFile).param("personId", "1"))
                .andExpect(status().isOk());

        when(personRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/image").file(jsonFile).param("personId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteImage() throws Exception {
        when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(delete("/image/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        when(imageRepository.findById(anyLong())).thenReturn(Optional.of(Datos.IMAGES_LIST.get(0)));
        mockMvc.perform(delete("/image/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}