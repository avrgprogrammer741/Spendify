package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.Wallet.Wallet;
import com.Spendify.Spendify.Wallet.WalletRepository;
import com.Spendify.Spendify.exception.DuplicateResourceException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final CurrencyRepository currencyRepository;
    private final WalletRepository walletRepository;
    private final BalanceDTOMapper balanceDTOMapper;

    @Autowired
    public List<BalanceDTO> getAllBalances() {
        return balanceRepository.findAll()
                .stream()
                .map(balanceDTOMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<BalanceDTO> getBalance(Long balanceId) {
        return balanceRepository.findById(balanceId)
                .map(balanceDTOMapper::map);
    }

    public Optional<BalanceDTO> updateBalance(BalanceUpdateRequest balanceUpdateRequest, Long balanceId) {
        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new ResourceNotFoundException("balance with id [%s] not found".formatted(balanceId)));
        balance.setAmount(balanceUpdateRequest.amount());
        balanceRepository.save(balance);
        return balanceRepository.findById(balanceId).map(balanceDTOMapper::map);
    }

    public void deleteBalance(Long balanceId) {
        balanceRepository.deleteById(balanceId);
    }

    public Optional<BalanceDTO> addBalance(BalanceAddRequest addRequest) {
        if (existsByCurrencyIdAndUserId(addRequest.currencyId(), addRequest.walletId())) {
            throw new DuplicateResourceException(
                    "balance with this currency already exists for this wallet"
            );
        }
        Currency currency;
        Wallet wallet;
        currency = currencyRepository.findById(addRequest.currencyId()).orElseThrow(() -> new ResourceNotFoundException(
                "currency with id [%s] not found".formatted(addRequest.currencyId())));
        wallet = walletRepository.findById(addRequest.walletId()).orElseThrow(() -> new ResourceNotFoundException(
                "wallet with id [%s] not found".formatted(addRequest.walletId())));
        Balance balance = Balance.builder().currency(currency).wallet(wallet).build();
        balanceRepository.save(balance);
        return this.getBalance(balance.getId());
    }

    @Autowired
    public BalanceService(CurrencyRepository currencyRepository, WalletRepository walletRepository, BalanceRepository balanceRepository, BalanceDTOMapper balanceDTOMapper) {
        this.currencyRepository = currencyRepository;
        this.walletRepository = walletRepository;
        this.balanceRepository = balanceRepository;
        this.balanceDTOMapper = balanceDTOMapper;
    }

    public boolean existsByCurrencyIdAndUserId(Long currencyId, Long walletId) {
        return balanceRepository.existsByCurrencyIdAndWalletId(currencyId, walletId);
    }
}