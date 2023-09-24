package com.Spendify.Spendify.Expense;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Debt.Debt;
import com.Spendify.Spendify.Invoice.Invoice;

public record ExpenseDTO(Long id,
                         Double quantity,
                         Long debt_id,
                         Long invoice_id
                         ) {

}
