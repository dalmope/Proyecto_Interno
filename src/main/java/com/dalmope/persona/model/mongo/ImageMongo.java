package com.dalmope.persona.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageMongo {
    private String id;
    private byte[] data;
    private Long personId;

    public ImageMongo(byte[] data, Long personID) {
        this.data = data;
        this.personId = personID;
    }
}
