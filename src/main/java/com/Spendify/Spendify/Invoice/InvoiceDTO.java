package com.Spendify.Spendify.Invoice;

import java.time.LocalDate;

public record InvoiceDTO(Long invoiceId,
                         LocalDate date,
                         Double price,
                         Double buyingprice,
                         Double sellingprice
                         ){
}
