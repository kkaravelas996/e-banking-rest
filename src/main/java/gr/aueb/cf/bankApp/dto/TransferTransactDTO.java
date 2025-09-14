package gr.aueb.cf.bankApp.dto;

import gr.aueb.cf.bankApp.model.Account;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
public class TransferTransactDTO {

    @Column(nullable = false)
    private Account senderAccount;

    @Column(nullable = false)
    private Account toAccount;

    @Column(nullable = false)
    private BigDecimal amount;

}
