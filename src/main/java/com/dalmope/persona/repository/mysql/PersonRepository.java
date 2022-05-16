package com.dalmope.persona.repository.mysql;

import com.dalmope.persona.model.mysql.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByNameOrEmail (String name, String email);
}
