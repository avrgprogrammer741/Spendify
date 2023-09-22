package com.Spendify.Spendify.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletDTOMapper walletDTOMapper;

    @Autowired
    public List<WalletDTO> getAllWallets() {
        return walletRepository.findAll()
                .stream()
                .map(walletDTOMapper)
                .collect(Collectors.toList());
    }

    public WalletService(WalletRepository walletRepository, WalletDTOMapper walletDTOMapper) {
        this.walletRepository = walletRepository;
        this.walletDTOMapper = walletDTOMapper;
    }

}
