package com.Spendify.Spendify.Currency.service;

import com.Spendify.Spendify.Currency.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {
    @Mock
    CurrencyRepository currencyRepository;

    @Mock
    CurrencyDTOMapper currencyDTOMapper;

    @InjectMocks
    CurrencyService currencyService;

    @Test
    void getById(){
        Long id = 111111111L;
        Currency currency = Currency.builder()
                .build();
        CurrencyDTO currencyDTO = CurrencyDTO.builder().build();
        when(currencyRepository.findById(id)).thenReturn(Optional.of(currency));
        when(currencyDTOMapper.map(currency)).thenReturn(currencyDTO);
        assertSame(currencyDTO, currencyService.getCurrency(id));
    }
}
