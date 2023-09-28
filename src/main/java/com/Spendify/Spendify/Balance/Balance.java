package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Wallet.Wallet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
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

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    @Column(name = "amount",
            precision = 2,
            nullable = false
    )
    private double amount;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    public Balance(Currency currency, Wallet wallet, double amount) {
        this.currency = currency;
        this.wallet = wallet;
        this.amount = amount;
    }
}
