package com.Spendify.Spendify.Wallet;

import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Wallet(Double quantity, User user) {
        this.quantity_pln = quantity;
        this.user = user;
    }
}
