package dev.danielmarcl.personapi.service;

import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.model.Person;
import dev.danielmarcl.personapi.repository.PersonRepository;
import dev.danielmarcl.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @Test
    void testWhenPersonDTOThenReturnSavedPerson() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person person = PersonUtils.createFakeEntity();

        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        Assertions.assertEquals(person, personService.savePerson(personDTO));
    }
}
