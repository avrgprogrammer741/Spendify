package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Expense.ExpenseAddRequest;
import com.Spendify.Spendify.Expense.ExpenseUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/balances")
@Validated
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{balanceId}")
    public ResponseEntity<BalanceDTO> getBalance(@PathVariable("balanceId") Long balanceId) {
        return balanceService.getBalance(balanceId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{balanceId}")
    public ResponseEntity<BalanceDTO> updateBalance(@Validated @RequestBody BalanceUpdateRequest balanceUpdateRequest,
                              @PathVariable("balanceId") Long balanceId) {
        return balanceService.updateBalance(balanceUpdateRequest, balanceId).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{balanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBalance(@PathVariable("balanceId") Long balanceId) {
        balanceService.deleteBalance(balanceId);
    }

    @PostMapping()
    public ResponseEntity<BalanceDTO> addBalance(@Validated @RequestBody BalanceAddRequest addRequest) {
        return balanceService.addBalance(addRequest).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }


    @GetMapping()
    public List<BalanceDTO> getAllBalances() {
        return balanceService.getAllBalances();
    }
}
