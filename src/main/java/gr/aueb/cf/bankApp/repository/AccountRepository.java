package gr.aueb.cf.bankApp.repository;

import gr.aueb.cf.bankApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByUser_Afm(String userAfm);

    Optional<Account> findByUser_Account_Ibank(String userAccountIbank);

}
