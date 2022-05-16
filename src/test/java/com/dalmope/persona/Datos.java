package com.dalmope.persona;

import com.dalmope.persona.DTO.ImageDTO;
import com.dalmope.persona.DTO.PersonDTO;
import com.dalmope.persona.model.mongo.ImageMongo;
import com.dalmope.persona.model.mysql.Image;
import com.dalmope.persona.model.mysql.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Datos {

        public final static List<Person> PERSON_LIST_IMAGE_NULL = Arrays.asList(
                new Person(1L, "Dalmope", "dalmope@dalmope.test", "1234567890", null),
                new Person(2L, "Alberto", "alberto@alberto.test", "1234567890", null),
                new Person(3L, "David", "david@david.test", "1234567890", null)
        );

        public final static List<PersonDTO> PERSONDTO_LIST_IMAGE_NULL = Arrays.asList(
                new PersonDTO(1L, "Dalmope", "dalmope@dalmope.test", null),
                new PersonDTO(2L, "Alberto", "alberto@alberto.test", null),
                new PersonDTO(3L, "David", "david@david.test", null)
        );

        public final static PersonDTO PERSONADTO_1 = new PersonDTO(1L, "Dalmope", "dalmope@dalmope.test", null);
        public final static PersonDTO PERSONADTO_2 = new PersonDTO(2L, "Alberto", "alberto@alberto.test", null);

        public final static ImageMongo IMAGE_MONGO_1 = new ImageMongo("1", "Dalmope".getBytes(), 1L);

        public final static List<ImageMongo> IMAGE_MONGO_PERSON_1 = List.of(new ImageMongo("1", "Dalmope".getBytes(), 1L));

        public final static List<Image> IMAGES_LIST = Arrays.asList(
                new Image(1L, "Dalmope".getBytes(), new Person(1L, "Dalmope", "dalmope@dalmope.test", "1234567890", null)),
                new Image(2L, "Alberto".getBytes(), new Person(2L, "Alberto", "alberto@alberto.test", "1234567890", null)),
                new Image(3L, "David".getBytes(), new Person(3L, "David", "david@david.test", "1234567890", null))
        );

        public final static List<ImageDTO> IMAGESDTO_LIST = Arrays.asList(
                new ImageDTO("1", "Dalmope".getBytes()),
                new ImageDTO("2", "Alberto".getBytes()),
                new ImageDTO("3", "David".getBytes())
        );

        public final static Image IMAGES_2 = new Image(1L, "Dalmope".getBytes(), null);

        public final static List<ImageMongo> IMAGES_MONGO_LIST = Arrays.asList(
                new ImageMongo("1", "Dalmope".getBytes(), 1L),
                new ImageMongo("2", "Alberto".getBytes(), 2L),
                new ImageMongo("3", "David".getBytes(), 3L)
        );

        public final static List<Person> PERSON_LIST = Arrays.asList(
                new Person(1L, "Dalmope", "dalmope@dalmope.test", "1234567890", Set.of()),
                new Person(2L, "Alberto", "alberto@alberto.test", "1234567890", Set.of()),
                new Person(3L, "David", "david@david.test", "1234567890", Set.of())
        );

        public final static List<ImageDTO> LIST_IMAGESDTO = List.of();

        public final static Image IMAGE_1 = new Image(1L, "Dalmope".getBytes(), new Person(1L, "Dalmope", "dalmope@dalmope.test", "1234567890", null));

}

