package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Expense.ExpenseAddRequest;
import com.Spendify.Spendify.Expense.ExpenseUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/balances")
public class BalanceController {
    private final BalanceService balanceService;

//    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{balanceId}")
    public BalanceDTO getBalance(@PathVariable("balanceId") Long balanceId) {
        return balanceService.getBalance(balanceId);
    }

    @PatchMapping("/{balanceId}")
    public void updateBalance(@RequestBody BalanceUpdateRequest balanceUpdateRequest,
                              @PathVariable("balanceId") Long balanceId) {
        balanceService.updateBalance(balanceUpdateRequest, balanceId);
    }

    @DeleteMapping("/{balanceId}")
    public void deleteBalance(@PathVariable("balanceId") Long balanceId) {
        balanceService.deleteBalance(balanceId);
    }

    @PostMapping()
    public void addBalance(@RequestBody BalanceAddRequest addRequest) {
        balanceService.addBalance(addRequest);
    }


    @GetMapping()
    public List<BalanceDTO> getAllBalances() {
        return balanceService.getAllBalances();
    }
}
