package gr.aueb.cf.bankApp.dto;

import gr.aueb.cf.bankApp.core.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalInfoDTO {

    private String identityNumber;

    private String fatherFirstname;

    private String motherFirstname;

    private String motherLastname;

    private LocalDate dateOfBirth;

    private Gender gender;


}
