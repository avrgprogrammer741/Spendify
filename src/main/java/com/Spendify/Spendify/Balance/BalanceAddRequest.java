package com.Spendify.Spendify.Balance;

public record BalanceAddRequest (Long currencyId,
                                 Long walletId,
                                 Double amount
                                 ){
}
