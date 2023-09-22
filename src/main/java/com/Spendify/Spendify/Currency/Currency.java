package com.Spendify.Spendify.Currency;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "currencies",
        uniqueConstraints = {
                @UniqueConstraint(name = "currency_name_unique", columnNames = "name")})
@Entity
public class Currency {
    @Id
    @SequenceGenerator(name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(
            name = "name",
            length = 50,
            nullable = false
    )
    private String name;
    public Currency(String name){
        this.name = name;
    }
}
