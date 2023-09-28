package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyDTO;
import com.Spendify.Spendify.Currency.CurrencyDTOMapper;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.Expense.ExpenseAddRequest;
import com.Spendify.Spendify.Expense.ExpenseDTO;
import com.Spendify.Spendify.Expense.ExpenseUpdateRequest;
import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Wallet.Wallet;
import com.Spendify.Spendify.Wallet.WalletRepository;
import com.Spendify.Spendify.exception.DuplicateResourceException;
import com.Spendify.Spendify.exception.FieldRequiredException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
                .map(balanceDTOMapper)
                .collect(Collectors.toList());
    }

    public BalanceDTO getBalance(Long balanceId) {
        return balanceRepository.findById(balanceId)
                .map(balanceDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(balanceId)
                ));
    }

    public void updateBalance(BalanceUpdateRequest balanceUpdateRequest, Long balanceId) {
        Balance balance = balanceRepository.findById(balanceId).orElseThrow(() -> new ResourceNotFoundException(
                "user with id [%s] not found".formatted(balanceId)));
        if (balanceUpdateRequest.amount() != null)
            balance.setAmount(balanceUpdateRequest.amount());
        balanceRepository.save(balance);
    }

    public void deleteBalance(Long balanceId) {
        balanceRepository.findById(balanceId).orElseThrow(() -> new ResourceNotFoundException(
                "user with id [%s] not found".formatted(balanceId)
        ));
        balanceRepository.deleteById(balanceId);
    }

    public void addBalance(BalanceAddRequest addRequest) {
        if (existsByCurrencyIdAndUserId(addRequest.currencyId(), addRequest.walletId())) {
            throw new DuplicateResourceException(
                    "balance with this currency already exists for this wallet"
            );
        }
        Currency currency;
        Wallet wallet;
        if (addRequest.currencyId() != null)
            currency = currencyRepository.findById(addRequest.currencyId()).orElseThrow(() -> new ResourceNotFoundException(
                    "currency with id [%s] not found".formatted(addRequest.currencyId())));
        else throw new FieldRequiredException("Fill this field: currencyId");
        if (addRequest.walletId() != null)
            wallet = walletRepository.findById(addRequest.walletId()).orElseThrow(() -> new ResourceNotFoundException(
                    "wallet with id [%s] not found".formatted(addRequest.walletId())));
        else throw new FieldRequiredException("Fill this field: walletId");
        Balance balance = new Balance();
        balance.setCurrency(currency);
        balance.setWallet(wallet);
        if (addRequest.amount() != null)
            balance.setAmount(addRequest.amount());
        else
            throw new FieldRequiredException("Fill this field: amount");
        balanceRepository.save(balance);
    }

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