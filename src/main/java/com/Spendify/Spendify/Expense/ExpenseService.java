package com.Spendify.Spendify.Expense;

//import com.Spendify.Spendify.Debt.Debt;
//import com.Spendify.Spendify.Debt.DebtRepository;
import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Invoice.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
//    private final DebtRepository debtRepository;
    private final InvoiceRepository invoiceRepository;
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
    public void updateExpense (ExpenseUpdateRequest expenseUpdateRequest, Long expenseId) {
        Expense expense = expenseRepository.getReferenceById(expenseId);
        expense.setQuantity(expenseUpdateRequest.quantity());
        expense.setLeft(expenseUpdateRequest.left());
        expenseRepository.save(expense);
    }
    public void deleteExpense (Long expenseId) {
        Expense expense = expenseRepository.getReferenceById(expenseId);
        expenseRepository.delete(expense);
    }
    public void addExpense(ExpenseAddRequest addRequest) {
//        Debt debt = debtRepository.findById(addRequest.debtId())
//                .orElseThrow(() -> new IllegalArgumentException("Debt not found with ID: " + addRequest.debtId()));

        Invoice invoice = invoiceRepository.findById(addRequest.invoiceId())
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found with ID: " + addRequest.invoiceId()));

        Expense expense = new Expense();
//        expense.setDebt(debt);
        expense.setInvoice(invoice);
        expense.setQuantity(addRequest.quantity());
        expense.setDate(addRequest.date());
        expense.setLeft(addRequest.left());
        expenseRepository.save(expense);
    }

    public ExpenseService(ExpenseRepository expenseRepository,
//                          DebtRepository debtRepository,
                          InvoiceRepository invoiceRepository,
                          ExpenseDTOMapper expenseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseDTOMapper = expenseDTOMapper;
//        this.debtRepository = debtRepository;
        this.invoiceRepository =invoiceRepository ;
    }

}
