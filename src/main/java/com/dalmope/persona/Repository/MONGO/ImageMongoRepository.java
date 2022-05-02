package com.dalmope.persona.Repository.MONGO;

import com.dalmope.persona.Model.MONGO.ImageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageMongoRepository extends MongoRepository<ImageMongo, String> {
}
