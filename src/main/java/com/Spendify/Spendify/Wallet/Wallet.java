package com.Spendify.Spendify.Wallet;

import com.Spendify.Spendify.Balance.Balance;
import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Wallet {
    @Id
    @SequenceGenerator(name = "balance_sequence",
            sequenceName = "balance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "balance_sequence"
    )
    private Long Id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "wallet",
            cascade = CascadeType.ALL
    )
    private List<Balance> balances;
    public Wallet(User user) {
        this.user = user;
    }
}
