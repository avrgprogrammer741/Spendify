package com.Spendify.Spendify.Expense;

import lombok.Builder;

import java.util.Date;

@Builder
public record ExpenseDTO(Long id,
                         Double quantity,
                         Date date,
                         Double amountLeft) {
}
