package com.Spendify.Spendify.Currency;

import lombok.Builder;

@Builder
public record CurrencyDTO
    (
    Long id,
    String name)
{}
