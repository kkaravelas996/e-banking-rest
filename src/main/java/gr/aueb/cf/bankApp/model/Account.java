package gr.aueb.cf.bankApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ibank",unique = true,updatable = false)
    private String ibank;


    @Column(name = "balance")
    private BigDecimal balance;

    @PrePersist
    public void generateIbank(){
        this.ibank = initializeIbank()  ;
    }

    private String initializeIbank(){
        StringBuilder sb = new StringBuilder("GR");
        for (int i = 0; i<10; i++){
            sb.append((int)(Math.random()*10));
        }
        return sb.toString();
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
