package com.Spendify.Spendify.Balance;

import lombok.Builder;

@Builder
public record BalanceDTO (
    Long id,
    Long currencyId,
    Long walletId,
    double amount){
}
