package com.Spendify.Spendify.Debt;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DebtDTOMapper implements Function<Debt, DebtDTO> {
    public DebtDTO apply(Debt debt) {
        return new DebtDTO(
                debt.getId(),
                debt.getDate(),
                debt.getExpenses(),
                debt.getUsers()
        );
    }
}
