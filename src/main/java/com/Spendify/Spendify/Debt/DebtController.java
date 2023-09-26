//package com.Spendify.Spendify.Debt;
//import com.Spendify.Spendify.Expense.ExpenseAddRequest;
//import com.Spendify.Spendify.Expense.ExpenseUpdateRequest;
//import com.Spendify.Spendify.User.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/v1/debts/")
//public class DebtController {
//    private final DebtService debtService;
//
//    @Autowired
//    public DebtController(DebtService debtService) {
//        this.debtService = debtService;
//    }
//    @GetMapping()
//    public List<DebtDTO> getDebts() {
//        return debtService.getAllDebts();
//    }
//    @GetMapping("{debtId}")
//    public DebtDTO getDebt(@PathVariable ("debtId") Long debtId)
//    {
//        return debtService.getUserDebt(debtId);
//    }
//    @DeleteMapping("/{debtId}")
//    public void deleteDebt(@PathVariable("debtId") Long debtId)
//    {
//        debtService.deleteDebt(debtId);
//    }
//    @PostMapping
//    public void addDebt(@RequestBody DebtAddRequest addRequest) {
//        debtService.addDebt(addRequest);
//    }
//    @PatchMapping("/{debtId}")
//    public void updateDebt(@RequestBody DebtUpdateRequest debtUpdateRequest,
//                              @PathVariable("debtId") Long debtId) {
//        debtService.updateDebt(debtUpdateRequest, debtId);
//    }
//}
