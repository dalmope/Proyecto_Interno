package com.dalmope.persona.repository.mongo;

import com.dalmope.persona.model.mongo.ImageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// Extending the MongoRepository interface.
public interface ImageMongoRepository extends MongoRepository<ImageMongo, String> {
    List<ImageMongo> findByPersonId(Long personId);
    void deleteByPersonId(Long id);
}
