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
                balance.getWallet().getId(),
                balance.getAmount()
        );
    }
    public BalanceDTO map(Balance balance) {
        return BalanceDTO.builder()
                .id(balance.getId())
                .currencyId(balance.getCurrency().getId())
                .walletId(balance.getWallet().getId())
                .amount(balance.getAmount())
                .build();
    }

    public Balance map(BalanceUpdateRequest balanceUpdateRequest) {
        return Balance.builder()
                .amount(balanceUpdateRequest.amount())
                .build();
    }

}
