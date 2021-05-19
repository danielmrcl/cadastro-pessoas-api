package dev.danielmarcl.personapi.builder;

import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.dto.PhoneDTO;
import lombok.Builder;

import java.util.List;

@Builder
public class PersonDTOBuilder {
    @Builder.Default
    private long id = 1L;

    @Builder.Default
    private String firstName = "João";

    @Builder.Default
    private String lastName = "Pé de Feijão";

    @Builder.Default
    private String cpf = "11122233344";

    @Builder.Default
    private String birthDate = "01-01-1970";

    @Builder.Default
    private List<PhoneDTO> phones = List.of(PhoneDTOBuilder.builder().build().toPhoneDTO());

    public PersonDTO toPersonDTO() {
        return new PersonDTO(
                id,
                firstName,
                lastName,
                cpf,
                birthDate,
                phones
        );
    }
}
