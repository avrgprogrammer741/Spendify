package com.Spendify.Spendify.Invoice;

import lombok.Builder;
@Builder
public record InvoiceAddRequest(
                Long userId,
                Double price,
                Double exchangeRate,
                Long currencyId) { }