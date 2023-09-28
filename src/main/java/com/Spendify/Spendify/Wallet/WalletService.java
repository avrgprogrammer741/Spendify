package com.Spendify.Spendify.Wallet;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import com.Spendify.Spendify.exception.FieldRequiredException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletDTOMapper walletDTOMapper;
    private final UserRepository userRepository;

    public List<WalletDTO> getAllWallets() {
        return walletRepository.findAll()
                .stream()
                .map(walletDTOMapper)
                .collect(Collectors.toList());
    }

    public WalletDTO getWallet(Long walletId) {
        return walletRepository.findById(walletId)
                .map(walletDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "wallet with id [%s] not found".formatted(walletId)
                ));
    }

    public void deleteWallet(Long walletId) {
        walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException(
                "wallet with id [%s] not found".formatted(walletId)
        ));
        walletRepository.deleteById(walletId);
    }

    public void addWallet(WalletAddRequest addRequest) {
        User user;
        if (addRequest.userId() != null)
            user = userRepository.findById(addRequest.userId()).orElseThrow(() -> new ResourceNotFoundException(
                    "user with id [%s] not found".formatted(addRequest.userId())
            ));
        else
            throw new FieldRequiredException("Fill this field: userId");
        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);
    }

    public void updateWallet(Long walletId, WalletUpdateRequest updateRequest) {
        User user;
        Wallet wallet;
        if (updateRequest.userId() != null)
            user = userRepository.findById(updateRequest.userId()).orElseThrow(() -> new ResourceNotFoundException(
                    "user with id [%s] not found".formatted(updateRequest.userId())
            ));
        else
            throw new FieldRequiredException("Fill this field: userId");
        wallet = walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException(
                "wallet with id [%s] not found".formatted(walletId)
        ));
        wallet.setUser(user);
        walletRepository.save(wallet);
    }

    public WalletService(WalletRepository walletRepository, WalletDTOMapper walletDTOMapper, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.walletDTOMapper = walletDTOMapper;
        this.userRepository = userRepository;
    }

}
