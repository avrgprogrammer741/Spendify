package com.Spendify.Spendify.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyDTOMapper currencyDTOMapper;
    @Autowired
    public List<CurrencyDTO> getAllCurrencies(){
        return currencyRepository.findAll()
                .stream()
                .map(currencyDTOMapper)
                .collect(Collectors.toList());
    }
    public CurrencyService(CurrencyRepository currencyRepository, CurrencyDTOMapper currencyDTOMapper){
        this.currencyRepository = currencyRepository;
        this.currencyDTOMapper = currencyDTOMapper;
    }

}
