package dev.danielmarcl.personapi.service;

import dev.danielmarcl.personapi.builder.PersonDTOBuilder;
import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.exceptions.PersonNotFoundException;
import dev.danielmarcl.personapi.mappers.PersonMapper;
import dev.danielmarcl.personapi.model.Person;
import dev.danielmarcl.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private PersonService personService;

    @Test
    void testWhenPersonDTOThenReturnSavedPerson() {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedPersonSaved = personMapper.toModel(personDTO);

        /* When */
        when(personRepository.save(expectedPersonSaved)).thenReturn(expectedPersonSaved);

        /* Then */
        personService.savePerson(personDTO);

        /* Hamcrest asserts */
        assertThat(expectedPersonSaved.getId(), is(equalTo(personDTO.getId())));
        assertThat(expectedPersonSaved.getCpf(), is(equalTo(personDTO.getCpf())));
    }
}
