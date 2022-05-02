package com.dalmope.persona.Repository.MYSQL;

import com.dalmope.persona.Model.MYSQL.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    public Optional<Person> findByName(String name);
    public Optional<Person> findByEmail(String email);
    public Optional<Person> findByNameOrEmail (String name, String email);
}
