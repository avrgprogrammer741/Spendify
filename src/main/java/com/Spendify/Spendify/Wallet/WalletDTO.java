package com.Spendify.Spendify.Wallet;

import com.Spendify.Spendify.User.User;

public record WalletDTO(
        Long id,
        Double quantity_pln,
        User user
){}
