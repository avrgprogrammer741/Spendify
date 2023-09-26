package com.Spendify.Spendify.Balance;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyDTO;
import com.Spendify.Spendify.Currency.CurrencyDTOMapper;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.Debt.Debt;
import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.Expense.ExpenseAddRequest;
import com.Spendify.Spendify.Expense.ExpenseDTO;
import com.Spendify.Spendify.Expense.ExpenseUpdateRequest;
import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Wallet.Wallet;
import com.Spendify.Spendify.Wallet.WalletRepository;
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
    public List<BalanceDTO> getAllBalances(){
        return balanceRepository.findAll()
                .stream()
                .map(balanceDTOMapper)
                .collect(Collectors.toList());
    }
    public BalanceDTO getBalance(Long balanceId) {
        return balanceRepository
                .findById(balanceId)
                .map(balanceDTOMapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Balance not found with ID: " + balanceId));
    }
    public void updateBalance (BalanceUpdateRequest balanceUpdateRequest, Long balanceId) {
        Balance balance = balanceRepository.getReferenceById(balanceId);
        balance.setAmount(balanceUpdateRequest.amount());
        balanceRepository.save(balance);
    }
    public void deleteBalance (Long balanceId) {
        Balance balance = balanceRepository.getReferenceById(balanceId);
        balanceRepository.delete(balance);
    }
    public void addBalance(BalanceAddRequest addRequest) {
        Currency currency = currencyRepository.findById(addRequest.currencyId())
                .orElseThrow(() -> new IllegalArgumentException("Currency not found with ID: " + addRequest.currencyId()));

        Wallet wallet = walletRepository.findById(addRequest.walletId())
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found with ID: " + addRequest.walletId()));

        Balance balance = new Balance();
        balance.setCurrency(currency);
        balance.setWallet(wallet);
        balance.setAmount(addRequest.amount());
        balanceRepository.save(balance);
    }
    public BalanceService(CurrencyRepository currencyRepository, WalletRepository walletRepository, BalanceRepository balanceRepository, BalanceDTOMapper balanceDTOMapper){
        this.currencyRepository = currencyRepository;
        this.walletRepository = walletRepository;
        this.balanceRepository = balanceRepository;
        this.balanceDTOMapper = balanceDTOMapper;
    }

}