package gr.aueb.cf.bankApp.mapper;

import gr.aueb.cf.bankApp.dto.TransferReadOnlyDTO;
import gr.aueb.cf.bankApp.dto.UserReadOnlyDTO;
import gr.aueb.cf.bankApp.dto.UserRegistrationDTO;
import gr.aueb.cf.bankApp.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final PasswordEncoder passwordEncoder;

//    public TransferTransaction mapDtoToTransferEntity(TransferTransaction dto){
//
//        TransferTransaction transaction = new TransferTransaction();
//        transaction.setToAccount(dto.getToAccount());
//        transaction.setAmount(dto.getAmount());
//
//        return transaction;
//
//    }

    public TransferReadOnlyDTO mapToTransferReadOnlyDTO(TransferTransaction transaction){
        var dto = new TransferReadOnlyDTO();
        dto.setDescription(transaction.getDescription());
        dto.setAmount(transaction.getAmount());
        dto.setToIbank(transaction.getToAccount().getIbank());
        dto.setDate(transaction.getCreatedAt());

        return dto;
    }

    

    public User mapToUserEntityForCustomer(UserRegistrationDTO dto) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setIdentityNumber(dto.getIdentityNumber());
        personalInfo.setDateOfBirth(dto.getDateOfBirth());
        personalInfo.setFatherFirstName(dto.getFatherFirstname());
        personalInfo.setMotherFirstName(dto.getMotherFirstname());
        personalInfo.setMotherLastName(dto.getMotherFirstname());

        Customer customer = new Customer();
        customer.setPersonalInfo(personalInfo);

        Account account = new Account();

        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCustomer(customer);
        user.setAccount(account);

        return user;

    }

    public User mapToUserEntityForEmployee(UserRegistrationDTO dto) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setIdentityNumber(dto.getIdentityNumber());
        personalInfo.setDateOfBirth(dto.getDateOfBirth());
        personalInfo.setFatherFirstName(dto.getFatherFirstname());
        personalInfo.setMotherFirstName(dto.getMotherFirstname());
        personalInfo.setMotherLastName(dto.getMotherFirstname());

        Employee employee = new Employee();
        employee.setPersonalInfo(personalInfo);

        Account account = new Account();

        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmployee(employee);
        user.setAccount(account);

        return user;

    }

    public UserReadOnlyDTO mapToReadOnlyDTO(User user){
        return new UserReadOnlyDTO(user.getFirstname(), user.getLastname(), user.getAfm());
    }



}
