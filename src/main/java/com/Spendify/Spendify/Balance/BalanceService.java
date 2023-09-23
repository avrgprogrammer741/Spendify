package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.CurrencyDTO;
import com.Spendify.Spendify.Currency.CurrencyDTOMapper;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final BalanceDTOMapper balanceDTOMapper;
    @Autowired
    public List<BalanceDTO> getAllBalances(){
        return balanceRepository.findAll()
                .stream()
                .map(balanceDTOMapper)
                .collect(Collectors.toList());
    }
    public BalanceService(BalanceRepository balanceRepository, BalanceDTOMapper balanceDTOMapper){
        this.balanceRepository = balanceRepository;
        this.balanceDTOMapper = balanceDTOMapper;
    }

}