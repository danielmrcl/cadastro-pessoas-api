package dev.danielmarcl.personapi.builder;

import dev.danielmarcl.personapi.dto.PhoneDTO;
import dev.danielmarcl.personapi.enums.PhoneType;
import lombok.Builder;

@Builder
public class PhoneDTOBuilder {
    @Builder.Default
    private long id = 1L;

    @Builder.Default
    private PhoneType type = PhoneType.MOBILE;

    @Builder.Default
    private String number = "11233334444";

    public PhoneDTO toPhoneDTO () {
        return new PhoneDTO(
                id,
                type,
                number
        );
    }
}
