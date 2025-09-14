package gr.aueb.cf.bankApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferReadOnlyDTO {

    private  String description ;
    private LocalDateTime date;
    private BigDecimal amount;
    private String toIbank;


}
