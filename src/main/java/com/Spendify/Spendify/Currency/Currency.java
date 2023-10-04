package com.Spendify.Spendify.Currency;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "currencies",
        uniqueConstraints = {
                @UniqueConstraint(name = "currency_name_unique", columnNames = "name")})
@Entity
@Builder
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
}
