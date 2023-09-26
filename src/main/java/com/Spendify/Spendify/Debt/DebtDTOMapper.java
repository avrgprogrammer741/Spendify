//package com.Spendify.Spendify.Debt;
//
//import com.Spendify.Spendify.Expense.Expense;
//import com.Spendify.Spendify.User.User;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.function.Function;
//
//@Service
//public class DebtDTOMapper implements Function<Debt, DebtDTO> {
//
//    public DebtDTO apply(Debt debt) {
//        List<Long> userIds = debt.getUsers().stream()
//                .map(User::getId)
//                .toList();
//        List<Long> expensesIds = debt.getExpenses().stream()
//                .map(Expense::getId)
//                .toList();
//        return new DebtDTO(
//                debt.getId(),
//                debt.getDate(),
//                expensesIds,
//                userIds
//        );
//    }
//}
