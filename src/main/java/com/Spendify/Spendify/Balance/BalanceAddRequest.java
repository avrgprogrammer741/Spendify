package com.Spendify.Spendify.Balance;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BalanceAddRequest(
        @NotNull(message = "currencyId cannot be null")
        Long currencyId,
        @NotNull(message = "walletId cannot be null")
        Long walletId,
        @NotNull(message = "amount cannot be null")
        @DecimalMin(value = "0.01", message = "amount must be greater than or equal to 0.01")
        Double amount
) {
}
