package dev.danielmarcl.personapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data // implements Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode to class
@Builder // implements an builder() method to build an instance
@AllArgsConstructor // make an constructor with all arguments
@NoArgsConstructor // implements an constructor with no arguments
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Phone> phones;
}
