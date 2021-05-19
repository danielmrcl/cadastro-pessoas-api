package dev.danielmarcl.personapi.controller;

import dev.danielmarcl.personapi.builder.PersonDTOBuilder;
import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.exceptions.PersonNotFoundException;
import dev.danielmarcl.personapi.service.PersonService;
import dev.danielmarcl.personapi.utils.JsonConvertionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTests {

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()) // Pages Support
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView()) // Json Support
                .build();
    }

    @Test
    void testWhenPOSTIsCalledThenPersonIsCreated() throws Exception {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        /* When */
        when(personService.savePerson(personDTO)).thenReturn(personDTO);

        /* Then */
        mockMvc.perform(post("/api/v1/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(personDTO.getFirstName())))
                .andExpect(jsonPath("$.cpf", is(personDTO.getCpf())));
    }

    @Test
    void testWhenPOSTIsCalledWithInvalidParamsThenReturnError() throws Exception {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        personDTO.setLastName("");

        /* Then */
        mockMvc.perform(post("/api/v1/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConvertionUtils.asJsonString(personDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testWhenGETIsCalledWithValidIdThenReturnPersonById() throws Exception {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        /* When */
        when(personService.getPersonById(personDTO.getId())).thenReturn(personDTO);

        /* Then */
        mockMvc.perform(get("/api/v1/person" + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(personDTO.getFirstName())))
                .andExpect(jsonPath("$.cpf", is(personDTO.getCpf())));
    }

    @Test
    void testWhenGETIsCalledWithNotValidIdThenReturnNotFoundStatus() throws Exception {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        /* When */
        when(personService.getPersonById(personDTO.getId())).thenThrow(PersonNotFoundException.class);

        /* Then */
        mockMvc.perform(get("/api/v1/person" + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testWhenGETPersonsListReturnOkStatus() throws Exception {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        /* When */
        when(personService.getPersons()).thenReturn(Collections.singletonList(personDTO));

        /* Then */
        mockMvc.perform(get("/api/v1/person")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(personDTO.getFirstName())))
                .andExpect(jsonPath("$[0].cpf", is(personDTO.getCpf())));
    }

    @Test
    void testWhenDELETEPersonWithValidIdThenReturnNoContentStatus() throws Exception {
        /* Given */
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        doNothing().when(personService).deletePerson(personDTO.getId());

        mockMvc.perform(delete("/api/v1/person" + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testWhenDELETEPersonWithInvalidIdThenReturnNoContentStatus() throws Exception {
        doThrow(PersonNotFoundException.class).when(personService).deletePerson(0L);

        mockMvc.perform(delete("/api/v1/person" + "/" + 0L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
