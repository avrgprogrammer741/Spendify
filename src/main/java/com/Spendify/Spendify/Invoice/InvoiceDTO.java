package com.Spendify.Spendify.Invoice;

import java.util.Date;

public record InvoiceDTO(Long invoiceId,
                         Date date,
                         Double price,
                         Double buyingPrice,
                         Double sellingPrice
                         ){
}
