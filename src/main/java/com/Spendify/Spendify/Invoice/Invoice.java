package com.Spendify.Spendify.Invoice;

import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
    @Column(name = "invoiceId",
            length = 50,
            nullable = false
    )
    private Long invoiceId;
    @Column(name = "date",
            length = 50,
            nullable = false
    )
    private LocalDate date;
    @Column(name = "name",
            length = 50,
            nullable = false
    )
    private Double price;
    @Column(name = "price",
            length = 50,
            nullable = false
    )
    private Long idUser;
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
    @OneToMany(mappedBy = "invoice")
    private List<Expense> expense;




    public Invoice() {
    }

    public Invoice(Long id, LocalDate date, Double price, Long idUser, Double buyingprice, Double sellingPrice) {
        this.invoiceId = id;
        this.date = date;
        this.price = price;
        this.idUser = idUser;
        this.buyingPrice = buyingprice;
        this.sellingPrice = sellingPrice;
    }

    public Invoice(Double price, Double buyingPrice, Double sellingPrice) {
//        this.date = date;
        this.price = price;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }



    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoiceId +
                ", date=" + date +
                ", price=" + price +
                ", idUser=" + idUser +
                ", buyingprice=" + buyingPrice +
                ", sellingprice=" + sellingPrice +
                '}';
    }
}
