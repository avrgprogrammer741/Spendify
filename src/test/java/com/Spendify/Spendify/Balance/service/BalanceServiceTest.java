package com.Spendify.Spendify.Balance.service;

import com.Spendify.Spendify.Balance.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {
    @Mock
    BalanceRepository balanceRepository;

    @Mock
    BalanceDTOMapper balanceDTOMapper;

    @InjectMocks
    BalanceService balanceService;

    @Test
    void getById(){
        Long id = 1111111L;
        Balance balance = Balance.builder().build();
        BalanceDTO balanceDTO = BalanceDTO.builder().build();
        when(balanceRepository.findById(id)).thenReturn(Optional.of(balance));
        when(balanceDTOMapper.map(balance)).thenReturn(balanceDTO);
        assertSame(balanceDTO, balanceService.getBalance(id).get());
    }
}
