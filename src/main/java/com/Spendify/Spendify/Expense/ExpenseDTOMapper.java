package com.Spendify.Spendify.Expense;

import com.Spendify.Spendify.Balance.Balance;
import com.Spendify.Spendify.Balance.BalanceDTO;
import com.Spendify.Spendify.Balance.BalanceUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ExpenseDTOMapper implements Function<Expense, ExpenseDTO> {
    @Override
    public ExpenseDTO apply(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getQuantity(),
                expense.getDate(),
                expense.getAmountLeft()
        );
    }

    public ExpenseDTO map(Expense expense) {
        return ExpenseDTO.builder()
                .id(expense.getId())
                .quantity(expense.getQuantity())
                .date(expense.getDate())
                .left(expense.getAmountLeft())
                .build();
    }

    public Expense map(ExpenseUpdateRequest expenseUpdateRequest) {
        return Expense.builder()
                .amountLeft(expenseUpdateRequest.amountLeft())
                .build();
    }
}

