package com.Spendify.Spendify.Expense;

import java.util.Date;

public record ExpenseAddRequest(Double quantity,
//                                Long debtId,
                                Long invoiceId,
                                Double left,
                                Date date) {
}
