package com.Spendify.Spendify.Invoice;

import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
public record InvoiceAddRequest(
                Long userId,
                @NotNull
                Double price,
                Double exchangeRate,
                Long currencyId) { }