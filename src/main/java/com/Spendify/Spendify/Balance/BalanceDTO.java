package com.Spendify.Spendify.Balance;

public record BalanceDTO (
    Long id,
    Long userId,
    Long currencyId,
    Integer amount){
}
