package dev.danielmarcl.personapi.utils;

import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.model.Person;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PersonUtils {

    private static final Long PERSON_ID = 1L;
    private static final String FIRST_NAME = "João";
    private static final String LAST_NAME = "Pé de Feijão";
    private static final String CPF = "11111111111";
    private static final String BIRTH_DATE_DTO = "01-01-1970";
    private static final LocalDate BIRTH_DATE_ENTITY = LocalDate.of(1970, 1, 1);

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE_DTO)
                .phones(List.of(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF)
                .birthDate(BIRTH_DATE_ENTITY)
                .phones(List.of(PhoneUtils.createFakeEntity()))
                .build();
    }

}
