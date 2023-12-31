package com.Spendify.Spendify.Invoice;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
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

    @Column(name = "exchange_rate",
            length = 50,
            nullable = false
    )
    private Double exchangeRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;


    public Invoice() {
    }

    public Invoice(Date date, Double price, Currency currency, User user, Double exchangeRate) {
        this.date = date;
        this.price = price;
        this.user = user;
        this.exchangeRate = exchangeRate;
        this.currency = currency;
    }

    public Invoice(Date date, Double price, Currency currency, Double exchangeRate) {
        this.date = date;
        this.price = price;
        this.exchangeRate = exchangeRate;
        this.currency = currency;
    }
    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoiceId +
                ", date=" + date +
                ", price=" + price +
                ", sellingprice=" + exchangeRate +
                '}';
    }
}
