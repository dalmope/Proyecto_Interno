package com.dalmope.persona.Repository.MYSQL;

import com.dalmope.persona.Model.MYSQL.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByNameOrEmail (String name, String email);
}
