package com.Spendify.Spendify.Invoice;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
