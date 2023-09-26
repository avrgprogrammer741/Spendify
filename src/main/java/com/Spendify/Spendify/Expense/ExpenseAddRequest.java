package com.Spendify.Spendify.Expense;

public record ExpenseAddRequest(Double quantity,
                                Long debtId,
                                Long invoiceId) {
}
