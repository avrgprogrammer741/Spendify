package com.Spendify.Spendify.Expense;

import java.util.Date;

public record ExpenseDTO(Long id,
                               Double quantity,
                                Date date,
                               Double left) { }
