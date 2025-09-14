package gr.aueb.cf.bankApp.service;

import gr.aueb.cf.bankApp.core.exceptions.ObjectNotFoundException;
import gr.aueb.cf.bankApp.core.filters.Paginated;
import gr.aueb.cf.bankApp.core.filters.UserFilters;
import gr.aueb.cf.bankApp.core.specifications.UserSpecification;
import gr.aueb.cf.bankApp.dto.TransferReadOnlyDTO;
import gr.aueb.cf.bankApp.dto.UserReadOnlyDTO;
import gr.aueb.cf.bankApp.dto.UserRegistrationDTO;
import gr.aueb.cf.bankApp.core.enums.Role;
import gr.aueb.cf.bankApp.core.exceptions.InvalidArgumentException;
import gr.aueb.cf.bankApp.core.exceptions.ObjectAlreadyExistsException;
import gr.aueb.cf.bankApp.mapper.Mapper;
import gr.aueb.cf.bankApp.model.Account;
import gr.aueb.cf.bankApp.model.TransferTransaction;
import gr.aueb.cf.bankApp.model.User;
import gr.aueb.cf.bankApp.repository.AccountRepository;
import gr.aueb.cf.bankApp.repository.TransferTransactionRepository;
import gr.aueb.cf.bankApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final Mapper mapper;
    private final TransferTransactionRepository transferTransactionRepository;

    @Transactional(rollbackOn = Exception.class)
    public UserReadOnlyDTO saveUserCustomer(UserRegistrationDTO dto) throws
            ObjectAlreadyExistsException, InvalidArgumentException {
        if (dto.getRole() == Role.ADMIN) {
            throw new InvalidArgumentException("Warning", "You can either register as ADMIN or CUSTOMER!");
        }

        if (userRepository.findByAfm(dto.getAfm()).isPresent()) {
            throw new ObjectAlreadyExistsException("User", "User with AFM:" + dto.getAfm() + "already exists");
        }

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new ObjectAlreadyExistsException("User", "User with username" + dto.getUsername() + "already exists");
        }
        if (userRepository.findByCustomer_PersonalInfo_IdentityNumber(dto.getIdentityNumber()).isPresent()) {
            throw new ObjectAlreadyExistsException("USer", "User with identity number" + dto.getIdentityNumber() + " already exists");
        }

        User savedUser = mapper.mapToUserEntityForCustomer(dto);

        return mapper.mapToReadOnlyDTO(savedUser);

    }

    public UserReadOnlyDTO saveUserAsAdmin(UserRegistrationDTO dto) throws
            ObjectAlreadyExistsException, InvalidArgumentException {
        if (dto.getRole() == Role.CUSTOMER) {
            throw new InvalidArgumentException("Warning", "You can either register as ADMIN or CUSTOMER!");
        }
        if (userRepository.findByAfm(dto.getAfm()).isPresent()) {
            throw new ObjectAlreadyExistsException("User", "User with afm:" + dto.getAfm() + " already exists.");
        }

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new ObjectAlreadyExistsException("User", "User with username" + dto.getUsername() + "already exists");
        }
        if (userRepository.findByCustomer_PersonalInfo_IdentityNumber(dto.getIdentityNumber()).isPresent()) {
            throw new ObjectAlreadyExistsException("USer", "User with identity number" + dto.getIdentityNumber() + " already exists");
        }

        User savedUser = mapper.mapToUserEntityForCustomer(dto);

        return mapper.mapToReadOnlyDTO(savedUser);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

    @Transactional
    public TransferReadOnlyDTO saveTransfer(TransferTransaction dto)
            throws ObjectNotFoundException, InvalidArgumentException {
        User currentUser = getCurrentUser();

        Account toAccount = accountRepository.findByUser_Account_Ibank(dto.getToAccount().getIbank()).
                orElseThrow(()->
                        new InvalidArgumentException("Account","Account with IBANK:" + dto.getToAccount().getIbank() + " was not found!"));
        Account userAccount = accountRepository.findByUser_Account_Ibank(currentUser.getAccount().getIbank()).
                orElseThrow(()->new ObjectNotFoundException("Account","Your account was not found"));
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidArgumentException("InvalidArgument","Amount must be positive!");
        }

        if (toAccount.getIbank().equals(userAccount.getIbank())){
            throw new InvalidArgumentException("InvalidArgument"," You cannot transfer to the same account!");
        }

        if (userAccount.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new InvalidArgumentException("InvalidArgument", "You dont have sufficient balance for this transaction!");
        }
        userAccount.setBalance(userAccount.getBalance().subtract(dto.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(dto.getAmount()));

        TransferTransaction transaction = new TransferTransaction(null,userAccount,toAccount,dto.getAmount(),null);

        transferTransactionRepository.save(transaction);

        return mapper.mapToTransferReadOnlyDTO(transaction);

    }
    public Page<UserReadOnlyDTO> getPaginatedUsers(int page, int size){
        String defaultSort = "id";

        Pageable pageable = PageRequest.of(page,size, Sort.by(defaultSort).ascending());
        return userRepository.findAll(pageable).map(mapper:: mapToReadOnlyDTO);
    }
    public Page<UserReadOnlyDTO> getPaginatedSortedUsers(int page,int size,String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        return userRepository.findAll(pageable).map(mapper::mapToReadOnlyDTO);
    }

    @org.springframework.transaction.annotation.Transactional
    public Paginated<UserReadOnlyDTO> getUsersFilteredPaginated(UserFilters filters) {
        var filtered = userRepository.findAll(getSpecsFromFilters(filters), filters.getPageable());
        return new Paginated<>(filtered.map(mapper::mapToReadOnlyDTO));
    }

    private Specification<User> getSpecsFromFilters(UserFilters userFilters){
        Specification<User> spec = Specification.allOf();

        if (userFilters.getAfm() != null){
            spec = spec.and(UserSpecification.userAfmIs(userFilters.getAfm()));
        }

        return spec;
    }

}
