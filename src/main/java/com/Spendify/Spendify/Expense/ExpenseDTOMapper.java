package com.Spendify.Spendify.Expense;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ExpenseDTOMapper implements Function<Expense, ExpenseDTO> {
    @Override
    public ExpenseDTO apply(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getQuantity(),
//                expense.getDebt().getId(),
                expense.getInvoice().getInvoiceId(),
                expense.getDate(),
                expense.getAmountLeft()
        );
    }
}

