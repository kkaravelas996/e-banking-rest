package gr.aueb.cf.bankApp.repository;

import gr.aueb.cf.bankApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
    Optional<Employee> findByEmployeeVat(String employeeVat);

    Optional<Employee> findByUserAfm(String userAfm);

}
