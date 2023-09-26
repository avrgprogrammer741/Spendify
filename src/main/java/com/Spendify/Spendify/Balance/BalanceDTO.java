package com.Spendify.Spendify.Balance;

public record BalanceDTO (
    Long id,
    Long currencyId,
    Long walletId,
    double amount){
}
