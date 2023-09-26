package com.Spendify.Spendify.Invoice;


public record InvoiceAddRequest(
                Long userId,
                Double price,
                Double exchangeRate,
                Long currencyId) { }