package gr.aueb.cf.bankApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(unique = true,updatable = false)
    private String employeeVat;

    @PrePersist
    public void initializeUUID(){
        if (uuid==null) uuid = UUID.randomUUID().toString();
        if (employeeVat == null){
            employeeVat = generateVat();
        }
    }


    private String generateVat(){
        SecureRandom random = new SecureRandom();
        int digit = random.nextInt(10);
        return random.toString();
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;
}
