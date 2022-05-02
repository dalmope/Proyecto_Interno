package com.dalmope.persona.Repository.MYSQL;

import com.dalmope.persona.Model.MYSQL.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
