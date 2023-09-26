//package com.Spendify.Spendify.Debt;
//
//import com.Spendify.Spendify.Expense.Expense;
//import com.Spendify.Spendify.Expense.ExpenseRepository;
//import com.Spendify.Spendify.Invoice.Invoice;
//import com.Spendify.Spendify.User.User;
//import com.Spendify.Spendify.User.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class DebtService {
//    private final DebtRepository debtRepository;
//    private final ExpenseRepository expenseRepository;
//    private final UserRepository userRepository;
//    private final DebtDTOMapper debtDTOMapper;
//    public List<DebtDTO> getAllDebts() {
//        return debtRepository.findAll()
//                .stream()
//                .map(debtDTOMapper)
//                .collect(Collectors.toList());
//    }
//    public DebtService(DebtRepository debtRepository, ExpenseRepository expenseRepository, UserRepository userRepository, DebtDTOMapper debtDTOMapper) {
//        this.debtRepository = debtRepository;
//        this.expenseRepository = expenseRepository;
//        this.userRepository = userRepository;
//        this.debtDTOMapper = debtDTOMapper;
//    }
//    public void setDebt(Debt debt) {
//        debtRepository.save(debt);
//    }
//
//    public DebtDTO getUserDebt(Long debtId) {
//        return debtRepository
//                .findById(debtId)
//                .map(debtDTOMapper)
//                .orElseThrow(() -> new IllegalStateException("Debt not found with ID: " + debtId));
//    }
//
//    public void deleteDebt(Long debtId) {
//        Debt debt = debtRepository.getReferenceById(debtId);
//        debtRepository.delete(debt);
//    }
//
//    public void addDebt(DebtAddRequest addRequest) {
//
////        for (Long aLong : addRequest.expensesId()) {
////            Expense expense = expenseRepository.findById(aLong) .orElseThrow(() -> new IllegalArgumentException("Expense not found with ID: " + aLong));
////            User user=userRepository.findById(aLong).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + aLong));
////            Debt debt=new Debt();
////            debt.setExpenses(expense);
////            debt.setUsers(user);
////            debtRepository.save(debt);
////        }
//    }
//    public void updateDebt(DebtUpdateRequest debtUpdateRequest, Long debtId) {
////        Debt debt = debtRepository.getReferenceById(debtId);
////        debtRepository.save(debt);
//    }
//}
