package dev.danielmarcl.personapi.utils;

import dev.danielmarcl.personapi.dto.PhoneDTO;
import dev.danielmarcl.personapi.enums.PhoneType;
import dev.danielmarcl.personapi.model.Phone;

public class PhoneUtils {

    private static final long PHONE_ID = 1L;
    private static final PhoneType TYPE = PhoneType.MOBILE;
    private static final String NUMBER = "11943214321";

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .type(TYPE)
                .number(NUMBER)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .type(TYPE)
                .number(NUMBER)
                .build();
    }

}
