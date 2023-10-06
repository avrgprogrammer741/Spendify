package com.Spendify.Spendify.Balance;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BalanceUpdateRequest (
        @NotNull
        @DecimalMin(value = "0.01", message = "amount must be greater or equal to 0.01")
        Double amount
){
}
