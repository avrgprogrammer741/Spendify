package com.Spendify.Spendify.Expense;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ExpenseAddRequest(
        @NotNull(message = "quantity cannot be null")
        @DecimalMin(value = "0.01", message = "quantity must be greater or equal to 0.01")
        Double quantity,
        @NotNull(message = "amountLeft cannot be null")
        @DecimalMin(value = "0.00", message = "amountLeft must be greater or equal to 0.00")
        Double amountLeft
) {
}
