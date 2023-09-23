package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.Currency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "balances")
public class Balance {
    @Id
    @SequenceGenerator(name = "balance_sequence",
            sequenceName = "balance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "balance_sequence"
    )
    @Column(name = "balance_id",
            length = 50,
            nullable = false
    )
    private Long id;
    private Long idUser;


    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @Column(name = "amount",
            length = 50,
            nullable = false
    )
    private int amount;

    public Balance(Long idUser, Currency currency, int amount) {
        this.idUser = idUser;
        this.currency = currency;
        this.amount = amount;
    }
}
