package com.Spendify.Spendify.Expense;

import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExpenseDTOMapper implements Function<Expense, ExpenseDTO> {
    @Override
    public ExpenseDTO apply(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getQuantity(),
                expense.getCurrency().getId(),
                expense.getDebt().getId(),
                expense.getInvoice().getInvoiceId()
        );
    }
}

