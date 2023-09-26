package com.Spendify.Spendify.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses/")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<ExpenseDTO> getExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{expenseId}")
    public ExpenseDTO getExpenses(@PathVariable("expenseId") Long expenseId) {
        return expenseService.getExpense(expenseId);
    }

    @PutMapping("/{expenseId}")
    public void updateExpense(@RequestBody ExpenseUpdateRequest expenseUpdateRequest,
                               @PathVariable("expenseId") Long expenseId) {
        expenseService.updateExpense(expenseUpdateRequest, expenseId);
    }
    @DeleteMapping("/{expenseId}")
    public void updateExpense(@PathVariable("expenseId") Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }
    @PostMapping
    public void addExpense(@RequestBody ExpenseAddRequest addRequest) {
        expenseService.addExpense(addRequest);
    }
}
