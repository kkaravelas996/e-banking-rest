package gr.aueb.cf.bankApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Collate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transfers")
public class TransferTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_type",nullable = false,updatable = false)
    private final String description = "Transfer";

    @OneToOne
    @Column(name = "sender")
    private Account senderAccount;

    @OneToOne
    @Column(name = "receiver_account")
    private Account toAccount;


    private BigDecimal amount;

    private LocalDateTime createdAt;


    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }


}
