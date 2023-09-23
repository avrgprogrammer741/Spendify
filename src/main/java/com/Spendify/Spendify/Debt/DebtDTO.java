package com.Spendify.Spendify.Debt;

import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.User.User;

import java.util.List;
import java.util.Set;

public record DebtDTO(Long debtId,
                      java.util.Date date,
                      List<Long> expensesIds,
                      List<Long> UserIds) {}