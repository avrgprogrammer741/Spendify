package com.Spendify.Spendify.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletDTOMapper walletDTOMapper;

    public List<WalletDTO> getAllWallets() {
        return walletRepository.findAll()
                .stream()
                .map(walletDTOMapper)
                .collect(Collectors.toList());
    }
    public WalletDTO getWallet(Long walletId) {
        return walletRepository.findById(walletId)
                .map(walletDTOMapper)
                .orElseThrow(() -> new IllegalStateException("Expense not found with ID: " + walletId));
    }

    public WalletService(WalletRepository walletRepository, WalletDTOMapper walletDTOMapper) {
        this.walletRepository = walletRepository;
        this.walletDTOMapper = walletDTOMapper;
    }

}
