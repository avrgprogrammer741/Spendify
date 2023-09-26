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
    private Long Id;

    @Column(name = "quantity_pln",
            nullable = false,
            precision = 2
    )
    private Double quantity_pln;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "wallet",
            cascade = CascadeType.ALL
    )
    private List<Balance> balances;
    public Wallet(Double quantity, User user) {
        this.quantity_pln = quantity;
        this.user = user;
    }
}
