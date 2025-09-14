package gr.aueb.cf.bankApp.repository;

import gr.aueb.cf.bankApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByAfm(String afm);

    Optional<User> findByUsername(String username);

    Optional<User> findByCustomer_PersonalInfo_IdentityNumber(String customerPersonalInfoIdentityNumber);


}
