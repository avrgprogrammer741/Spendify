package com.Spendify.Spendify.Wallet;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class WalletDTOMapper implements Function<Wallet, WalletDTO> {
    @Override
    public WalletDTO apply(Wallet wallet){
        return new WalletDTO(
                wallet.getId(),
                wallet.getUser().getId()
        );
    }
}
