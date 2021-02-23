package dev.danielmarcl.personapi.service;

import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.mappers.PersonMapper;
import dev.danielmarcl.personapi.model.Person;
import dev.danielmarcl.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired // injetar um objeto do tipo especificado
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getPersons() {
        List<Person> listPerson = this.personRepository.findAll();

        return listPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Person savePerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = this.personRepository.save(personToSave);

        return savedPerson;
    }
}
