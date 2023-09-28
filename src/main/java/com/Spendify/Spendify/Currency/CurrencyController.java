package com.Spendify.Spendify.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping()
    public List<CurrencyDTO> getCurrencies() {
        return currencyService.getAllCurrencies();
    }
    @GetMapping("/{currencyId}")
    public CurrencyDTO getCurrencies(@PathVariable("currencyId") Long currencyId) {
        return currencyService.getCurrency(currencyId);
    }

}
