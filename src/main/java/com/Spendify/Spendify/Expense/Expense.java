package com.Spendify.Spendify.Expense;


import com.Spendify.Spendify.Invoice.Invoice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @SequenceGenerator(name = "expense_sequence", sequenceName = "expense_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_sequence")
    private Long id;
    @Column(name = "quantity", nullable = false, precision = 2)
    private Double quantity;

    @Column(name = "amount_left", nullable = false, precision = 2)
    private Double amountLeft;
    @Column(name = "Date", nullable = false)
    private Date date;

    public Expense(Double quantity, Date date, Double amountLeft) {
        this.quantity = quantity;
        this.amountLeft = amountLeft;
        this.date = date;
    }

}
