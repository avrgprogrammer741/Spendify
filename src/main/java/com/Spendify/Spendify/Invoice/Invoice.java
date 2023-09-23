package com.Spendify.Spendify.Invoice;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Invoice {
    @Id
    @SequenceGenerator(name = "invoice_sequence",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_sequence"
    )
    @Column(name = "invoice_id",
            length = 50,
            nullable = false
    )
    private Long invoiceId;
    @Column(name = "date",
            length = 50,
            nullable = false
    )
    private Date date;
    @Column(name = "price",
            length = 50,
            nullable = false
    )
    private Double price;

    @Column(name = "buying_price",
            length = 50,
            nullable = false
    )
    private Double buyingPrice;
    @Column(name = "selling_price",
            length = 50,
            nullable = false
    )
    private Double sellingPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "invoice")
    private List<Expense> expense;

    public Invoice() {
    }

    public Invoice(Date date, Double price, Currency currency, User user, Double buyingPrice, Double sellingPrice) {
        this.date = date;
        this.price = price;
        this.buyingPrice = buyingPrice;
        this.user = user;
        this.sellingPrice = sellingPrice;
        this.currency = currency;
    }

    public Invoice(Date date, Double price, Currency currency, Double buyingPrice, Double sellingPrice) {
        this.date = date;
        this.price = price;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.currency = currency;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoiceId +
                ", date=" + date +
                ", price=" + price +
                ", buyingprice=" + buyingPrice +
                ", sellingprice=" + sellingPrice +
                '}';
    }
}
