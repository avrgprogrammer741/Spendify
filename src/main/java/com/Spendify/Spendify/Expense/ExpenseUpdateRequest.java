package com.Spendify.Spendify.Expense;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ExpenseUpdateRequest(
        @NotNull
        @DecimalMin(value = "0.00", message = "amountLeft must be greater or equal to 0.00")
        Double amountLeft) {
}