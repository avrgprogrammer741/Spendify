package com.Spendify.Spendify.Expense;

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
    public ExpenseDTO getExpense(Long expenseId) {
        return expenseRepository
                .findById(expenseId)
                .map(expenseDTOMapper)
                .orElseThrow(() -> new IllegalStateException("Expense not found with ID: " + expenseId));
    }
    public void updateExpense (ExpenseUpdateOrAddRequest expenseUpdateOrAddRequest, Long expenseId) {
        Expense expense = expenseRepository.getReferenceById(expenseId);
        expense.setQuantity(expenseUpdateOrAddRequest.quantity());
        expenseRepository.save(expense);
    }
    public void deleteExpense (Long expenseId) {
        Expense expense = expenseRepository.getReferenceById(expenseId);
        expenseRepository.delete(expense);
    }
    public void addExpense(ExpenseAddRequest addRequest) {
//        TODO: figure out a way to add new elements with debt_id and invoice_id
    }

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseDTOMapper expenseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseDTOMapper = expenseDTOMapper;
    }
}
