package com.Spendify.Spendify.Invoice;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record InvoiceAddRequestDTO(
        @NotNull(message = "userId can't be null")
        Long userId,
        @NotNull(message = "price can't be null")
        @DecimalMin(value = "0.01", message = "price must be greater than or equal to 0.01")
        Double price,
        @NotNull(message = "exchangeRate can't be null")
        @Positive(message = "exchangeRate must be a positive number")
        @DecimalMin(value = "0.01", message = "exchangeRate must be greater than or equal to 0.01")
        Double exchangeRate,
        @NotNull(message = "currencyId can't be null")
        Long currencyId) {
}