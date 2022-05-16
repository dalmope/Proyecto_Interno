package com.dalmope.persona.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDTO {

    private String id;
    private byte[] data;

    public ImageDTO(byte[] bytes) {
        this.data = bytes;
    }
}


