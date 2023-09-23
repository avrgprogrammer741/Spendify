package com.Spendify.Spendify.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseDTOMapper expenseDTOMapper;

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseDTOMapper)
                .collect(Collectors.toList());

    }

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseDTOMapper expenseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseDTOMapper = expenseDTOMapper;
    }
}
