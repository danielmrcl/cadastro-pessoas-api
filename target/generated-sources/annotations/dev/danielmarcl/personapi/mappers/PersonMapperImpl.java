package dev.danielmarcl.personapi.mappers;

import dev.danielmarcl.personapi.dto.PersonDTO;
import dev.danielmarcl.personapi.dto.PhoneDTO;
import dev.danielmarcl.personapi.model.Person;
import dev.danielmarcl.personapi.model.Phone;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-18T20:38:37-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toModel(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        Person person = new Person();

        if ( personDTO.getBirthDate() != null ) {
            person.setBirthDate( LocalDate.parse( personDTO.getBirthDate(), DateTimeFormatter.ofPattern( "dd-MM-yyyy" ) ) );
        }
        person.setId( personDTO.getId() );
        person.setFirstName( personDTO.getFirstName() );
        person.setLastName( personDTO.getLastName() );
        person.setCpf( personDTO.getCpf() );
        person.setPhones( phoneDTOListToPhoneList( personDTO.getPhones() ) );

        return person;
    }

    @Override
    public PersonDTO toDTO(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( person.getId() );
        personDTO.setFirstName( person.getFirstName() );
        personDTO.setLastName( person.getLastName() );
        personDTO.setCpf( person.getCpf() );
        if ( person.getBirthDate() != null ) {
            personDTO.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( person.getBirthDate() ) );
        }
        personDTO.setPhones( phoneListToPhoneDTOList( person.getPhones() ) );

        return personDTO;
    }

    protected Phone phoneDTOToPhone(PhoneDTO phoneDTO) {
        if ( phoneDTO == null ) {
            return null;
        }

        Phone phone = new Phone();

        phone.setId( phoneDTO.getId() );
        phone.setType( phoneDTO.getType() );
        phone.setNumber( phoneDTO.getNumber() );

        return phone;
    }

    protected List<Phone> phoneDTOListToPhoneList(List<PhoneDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneDTO phoneDTO : list ) {
            list1.add( phoneDTOToPhone( phoneDTO ) );
        }

        return list1;
    }

    protected PhoneDTO phoneToPhoneDTO(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDTO phoneDTO = new PhoneDTO();

        phoneDTO.setId( phone.getId() );
        phoneDTO.setType( phone.getType() );
        phoneDTO.setNumber( phone.getNumber() );

        return phoneDTO;
    }

    protected List<PhoneDTO> phoneListToPhoneDTOList(List<Phone> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneDTO> list1 = new ArrayList<PhoneDTO>( list.size() );
        for ( Phone phone : list ) {
            list1.add( phoneToPhoneDTO( phone ) );
        }

        return list1;
    }
}
