package com.Spendify.Spendify.Expense;

import com.Spendify.Spendify.Balance.BalanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses/")
@Validated
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
    public ResponseEntity<ExpenseDTO> getExpense(@PathVariable("expenseId") Long expenseId) {
        return expenseService.getExpense(expenseId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseDTO> updateExpense(@Validated @RequestBody ExpenseUpdateRequest expenseUpdateRequest,
                              @PathVariable("expenseId") Long expenseId) {
        return expenseService.updateExpense(expenseUpdateRequest, expenseId).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{expenseId}")
    public void updateExpense(@PathVariable("expenseId") Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@Validated @RequestBody ExpenseAddRequest addRequest) {
        return expenseService.addExpense(addRequest).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }
}
