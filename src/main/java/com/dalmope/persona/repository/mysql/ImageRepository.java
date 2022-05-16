package com.dalmope.persona.repository.mysql;

import com.dalmope.persona.model.mysql.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
