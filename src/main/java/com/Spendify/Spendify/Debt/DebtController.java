package com.Spendify.Spendify.Debt;
import com.Spendify.Spendify.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/debts/")
public class DebtController {
    private final DebtService debtService;

    @Autowired
    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }
    @GetMapping()
    public List<DebtDTO> getDebts() {
        return debtService.getAllDebts();
    }
    @GetMapping("{userId}")
    public Optional<Debt> getUserDebt(@PathVariable Long userId)
    {
        return debtService.getUserDebt(userId);
    }

    @DeleteMapping
    public void deleteUserDebt(User user) throws Exception {
        debtService.deleteDebt(user);
    }
    @PutMapping
    public void setUserDebt(Debt debt)
    {
        debtService.setDebt(debt);
    }
}
