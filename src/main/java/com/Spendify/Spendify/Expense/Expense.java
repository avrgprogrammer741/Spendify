package com.Spendify.Spendify.Expense;

import com.Spendify.Spendify.Currency.Currency;
//import com.Spendify.Spendify.Debt.Debt;
import com.Spendify.Spendify.Debt.Debt;
import com.Spendify.Spendify.Invoice.Invoice;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private Double quantity;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;


    @ManyToOne
    @JoinColumn(name = "debt_id")
    private Debt debt;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Expense(Double quantity, Currency currency, Debt debt, Invoice invoice) {
        this.quantity = quantity;
        this.currency = currency;
        this.debt = debt;
        this.invoice = invoice;
    }

}
