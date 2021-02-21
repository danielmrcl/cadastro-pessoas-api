package dev.danielmarcl.personapi.model;

import dev.danielmarcl.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data // implements Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode to class
@Builder // implements an builder() method to build an instance
@AllArgsConstructor // make an constructor with all arguments
@NoArgsConstructor // implements an constructor with no arguments
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
