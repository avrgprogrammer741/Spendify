package com.Spendify.Spendify.Expense;

//import com.Spendify.Spendify.Debt.Debt;
import com.Spendify.Spendify.Invoice.Invoice;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @SequenceGenerator(name = "expense_sequence",
            sequenceName = "expense_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_sequence"
    )
    private Long id;
    @Column(name = "quantity",
            nullable = false,
            precision = 2
    )
    @JsonProperty("quantity")
    private Double quantity;
//
//    @ManyToOne
//    @JoinColumn(name = "debt_id")
//    private Debt debt;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @Column(name = "left",
            nullable = false,
            precision = 2
    )
    private Double left;
    @Column(name = "Date",
            nullable = false
    )
    private Date date;
    public Expense(Double quantity, Invoice invoice, Date date, Double left) {
        this.quantity = quantity;
//        this.debt = debt;
        this.invoice = invoice;
        this.left=left;
        this.date=date;
    }

}
