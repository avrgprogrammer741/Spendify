package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class BalanceDTOMapper implements Function<Balance,BalanceDTO> {
    @Override
    public BalanceDTO apply(Balance balance){
        return new BalanceDTO(
                balance.getId(),
                balance.getCurrency().getId(),
                balance.getIdUser(),
                balance.getAmount()
        );
    }

}
