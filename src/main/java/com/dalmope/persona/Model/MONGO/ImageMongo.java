package com.dalmope.persona.Model.MONGO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageMongo {
    private String id;
    private MultipartFile image;
}
