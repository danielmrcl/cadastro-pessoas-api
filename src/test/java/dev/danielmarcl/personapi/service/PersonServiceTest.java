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

import java.util.Collections;
import java.util.List;
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

    @Test
    void testWhenPersonIdExistsThenReturnPersonById() throws PersonNotFoundException {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedPersonSaved = personMapper.toModel(personDTO);

        /* When */
        when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(expectedPersonSaved));

        /* Then */
        personService.getPersonById(personDTO.getId());

        assertThat(expectedPersonSaved.getId(), is(equalTo(personDTO.getId())));
    }

    @Test
    void testWhenPersonIdNotExistsThenThrownNotFoundException() {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        /* When */
        when(personRepository.findById(personDTO.getId())).thenReturn(Optional.empty());

        /* Then */
        assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(personDTO.getId()));
    }

    @Test
    void testWhenGetPersonsListReturnPersonList() {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedPerson = personMapper.toModel(personDTO);

        /* When */
        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedPerson));

        /* Then */
        List<PersonDTO> foundListPersonDTO = personService.getPersons();
        assertThat(foundListPersonDTO, is(not(empty())));
        assertThat(foundListPersonDTO.get(0), is(equalTo(personDTO)));
    }

    @Test
    void testWhenGetPersonsListReturnEmptyList() {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        /* When */
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        /* Then */
        List<PersonDTO> foundListPersonDTO = personService.getPersons();
        assertThat(foundListPersonDTO, is(empty()));
    }

    @Test
    void testWhenDeletePersonWithValidIdThenDeletePerson() throws PersonNotFoundException {
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedPerson = personMapper.toModel(personDTO);

        when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(expectedPerson));
        doNothing().when(personRepository).delete(expectedPerson);

        personService.deletePerson(personDTO.getId());

        verify(personRepository, times(1)).findById(personDTO.getId());
        verify(personRepository, times(1)).delete(expectedPerson);
    }
}
