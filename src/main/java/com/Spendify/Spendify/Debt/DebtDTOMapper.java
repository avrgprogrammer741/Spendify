package com.Spendify.Spendify.Debt;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DebtDTOMapper implements Function<Debt, DebtDTO> {
    public DebtDTO apply(Debt debt) {
        return new DebtDTO(
                debt.getDebtId(),
                debt.getDate(),
                debt.getExpenses(),
                debt.getUsers()
        );
    }
}
