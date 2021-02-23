package dev.danielmarcl.personapi.controller;

import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.model.Person;
import dev.danielmarcl.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPerson() {
        return personService.getPersons();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person postPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.savePerson(personDTO);
    }
}
