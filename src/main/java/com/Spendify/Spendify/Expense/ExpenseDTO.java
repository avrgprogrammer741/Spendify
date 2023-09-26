package com.Spendify.Spendify.Expense;

import java.util.Date;

public record ExpenseDTO(Long id,
                               Double quantity,
//                               Long debt_id,
                               Long invoice_id,
                                Date date,
                               Double left) { }
