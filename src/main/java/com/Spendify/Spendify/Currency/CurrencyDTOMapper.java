package com.Spendify.Spendify.Currency;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CurrencyDTOMapper implements Function<Currency, CurrencyDTO> {
    @Override
    public CurrencyDTO apply(Currency currency){
        return new CurrencyDTO(
                currency.getId(),
                currency.getName()
        );
    }
    public CurrencyDTO map(Currency currency) {
        return CurrencyDTO.builder().
                id(currency.getId()).
                name(currency.getName())
                .build();
    }
}
