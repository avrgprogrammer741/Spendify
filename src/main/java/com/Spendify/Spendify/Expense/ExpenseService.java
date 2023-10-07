package com.Spendify.Spendify.Expense;

//import com.Spendify.Spendify.Debt.Debt;
//import com.Spendify.Spendify.Debt.DebtRepository;

import com.Spendify.Spendify.Invoice.InvoiceRepository;
import com.Spendify.Spendify.exception.FieldRequiredException;
import com.Spendify.Spendify.exception.QuantityBiggerThanAmountLeftException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    //    private final DebtRepository debtRepository;
    private final InvoiceRepository invoiceRepository;
    private final ExpenseDTOMapper expenseDTOMapper;


    public ExpenseService(ExpenseRepository expenseRepository,
                          InvoiceRepository invoiceRepository,
                          ExpenseDTOMapper expenseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseDTOMapper = expenseDTOMapper;
        this.invoiceRepository = invoiceRepository;
    }

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseDTOMapper)
                .collect(Collectors.toList());

    }

    public Optional<ExpenseDTO> getExpense(Long expenseId) {
        return expenseRepository
                .findById(expenseId)
                .map(expenseDTOMapper::map);
    }

    public Optional<ExpenseDTO> updateExpense(ExpenseUpdateRequest expenseUpdateRequest, Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(
                "expense with id [%s] not found".formatted(expenseId)
        ));

        if (expenseUpdateRequest.amountLeft() != null)
            expense.setAmountLeft(expenseUpdateRequest.amountLeft());
        else
            throw new FieldRequiredException("Fill this field: amount_left");
        if (expenseUpdateRequest.amountLeft() > expense.getQuantity())
            throw new QuantityBiggerThanAmountLeftException("Amount left is bigger than quantity");
        expenseRepository.save(expense);
        return expenseRepository.findById(expenseId).map(expenseDTOMapper::map);
    }

    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(
                "expense with id [%s] not found".formatted(expenseId)
        ));
        expenseRepository.delete(expense);
    }

    public Optional<ExpenseDTO> addExpense(ExpenseAddRequest addRequest) {
        Expense expense = new Expense();
        if (addRequest.quantity() != null)
            expense.setQuantity(addRequest.quantity());
        else
            throw new FieldRequiredException("Fill this field: quantity");
        expense.setDate(new Date());
        if (addRequest.amountLeft() != null)
            expense.setAmountLeft(addRequest.amountLeft());
        else
            throw new FieldRequiredException("Fill this field: amount_left");
        if (addRequest.amountLeft() > addRequest.quantity())
            throw new QuantityBiggerThanAmountLeftException("Amount left is bigger than quantity");
        expenseRepository.save(expense);
        return this.getExpense(expense.getId());
    }
}
