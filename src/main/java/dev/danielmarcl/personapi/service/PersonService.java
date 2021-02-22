package dev.danielmarcl.personapi.service;

import dev.danielmarcl.personapi.model.Person;
import dev.danielmarcl.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired // injetar um objeto do tipo especificado
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return this.personRepository.findAll();
    }

    public Person savePerson(Person person) {
        Person savedPerson = this.personRepository.save(person);
        return savedPerson;
    }
}
