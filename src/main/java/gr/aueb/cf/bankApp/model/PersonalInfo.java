package gr.aueb.cf.bankApp.model;

import gr.aueb.cf.bankApp.core.enums.Gender;
import gr.aueb.cf.bankApp.core.enums.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "personal_info")
public class PersonalInfo extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String identityNumber;

    @Column(nullable = false)
    private LocalDateTime dateOfBirth;

    @Column(nullable = false)
    private String fatherFirstName;

    @Column(nullable = false)
    private String motherFirstName;

    @Column(nullable = false)
    private String motherLastName;

    @OneToOne(mappedBy = "personalInfo")
    private Customer customer;

    @OneToOne(mappedBy = "personalInfo")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}
