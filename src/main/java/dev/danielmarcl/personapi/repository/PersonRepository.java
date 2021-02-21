package dev.danielmarcl.personapi.repository;

import dev.danielmarcl.personapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
