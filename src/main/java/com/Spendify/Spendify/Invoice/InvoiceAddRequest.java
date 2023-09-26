package com.Spendify.Spendify.Invoice;

import java.util.Date;

public record InvoiceAddRequest(
                Long userId,
                Date date,
                Double price,
                Double buyingPrice,
                Double sellingPrice,
                Long currencyId) { }