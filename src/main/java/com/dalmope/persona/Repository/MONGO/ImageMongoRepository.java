package com.dalmope.persona.Repository.MONGO;

import com.dalmope.persona.Model.MYSQL.MONGO.ImageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ImageMongoRepository extends MongoRepository<ImageMongo, String> {
    List<ImageMongo> findByPersonId(Long personaId);
    void deleteByPersonId(Long id);
}
