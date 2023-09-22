package com.Spendify.Spendify.Expense;

import com.Spendify.Spendify.Currency.Currency;

public record ExpenseDTO(Long id,
                         Double quantity,
                         Currency currency
                         ) {
}
