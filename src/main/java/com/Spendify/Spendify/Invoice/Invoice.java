package com.Spendify.Spendify.Invoice;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table
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
    private Long invoiceId;
    private LocalDate date;
    private Double price;
    private Long idUser;
    private Double buyingprice;
    private Double sellingprice;

    public Invoice() {
    }

    public Invoice(Long id, LocalDate date, Double price, Long idUser, Double buyingprice, Double sellingprice) {
        this.invoiceId = id;
        this.date = date;
        this.price = price;
        this.idUser = idUser;
        this.buyingprice = buyingprice;
        this.sellingprice = sellingprice;
    }

    public Invoice(Double price, Double buyingprice, Double sellingprice) {
//        this.date = date;
        this.price = price;
        this.buyingprice = buyingprice;
        this.sellingprice = sellingprice;
    }



    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + invoiceId +
                ", date=" + date +
                ", price=" + price +
                ", idUser=" + idUser +
                ", buyingprice=" + buyingprice +
                ", sellingprice=" + sellingprice +
                '}';
    }
}
