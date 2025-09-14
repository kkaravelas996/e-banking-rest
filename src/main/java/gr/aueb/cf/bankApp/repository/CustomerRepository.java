package gr.aueb.cf.bankApp.repository;

import gr.aueb.cf.bankApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByUserAfm(String userAfm);

    Optional<Customer> findByUserUsername(String username);
}
