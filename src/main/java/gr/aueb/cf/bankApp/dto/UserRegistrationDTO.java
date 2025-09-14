package gr.aueb.cf.bankApp.dto;

import gr.aueb.cf.bankApp.core.enums.Gender;
import gr.aueb.cf.bankApp.core.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.SocketTimeoutException;
import java.security.PrivateKey;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {

    @NotEmpty(message = "Firstname is mandatory")
    @Size(min = 2,max = 24,message = "Firstname must contain at least 2 characters")
    private String firstname;

    @NotEmpty(message = "Firstname is mandatory")
    @Size(min = 2,max = 24,message = "Lastname must contain at least 2 characters")
    private String lastname;

    @NotEmpty(message = "Username must not be empty")
    @Email(message = "Invalid username")
    private String username;

    @NotEmpty
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[@#$%^&*!]).{8,}$",message = "Invalid password")
    private String password;

    @NotEmpty(message = "AFM is essential in order to crate your account")
    @Pattern(regexp = "^\\d{9}$")
    private String afm;

    @NotEmpty(message = "This field is mandatory")
    private String identityNumber;

    @NotNull(message = "Date of birth must not be empty")
    private LocalDateTime dateOfBirth;

    @NotEmpty(message = "This field is mandatory")
    private String fatherFirstname;

    @NotEmpty(message = "This field is mandatory")
    private String motherFirstname;

    @NotEmpty(message = "This field is mandatory")
    private String motherLastname;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "Role must not be empty")
    private Role role;

}
