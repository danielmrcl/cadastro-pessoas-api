package dev.danielmarcl.personapi.controller;

import dev.danielmarcl.personapi.builder.PersonDTOBuilder;
import dev.danielmarcl.personapi.dto.PersonDTO;
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
}
