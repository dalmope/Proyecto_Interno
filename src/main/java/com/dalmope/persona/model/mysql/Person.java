package com.dalmope.persona.model.mysql;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();
}
