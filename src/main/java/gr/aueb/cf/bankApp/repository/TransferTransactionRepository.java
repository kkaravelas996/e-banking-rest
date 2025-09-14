package gr.aueb.cf.bankApp.repository;

import gr.aueb.cf.bankApp.model.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransferTransactionRepository extends
        JpaRepository<TransferTransaction,Long>, JpaSpecificationExecutor<TransferTransaction> {

}
