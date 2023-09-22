package com.Spendify.Spendify.Debt;

import com.Spendify.Spendify.Expense.Expense;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Debt {
    @Id
    @SequenceGenerator(name = "debt_sequence",
            sequenceName = "debt_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "debt_sequence"
    )
    private Long Id;
    @OneToMany(mappedBy = "debt")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Expense> expenses;
    @Column(nullable = false)
    private Date date;
}
