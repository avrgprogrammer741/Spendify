package com.Spendify.Spendify.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/wallets")
public class WalletController {
    private final WalletService walletService;
    @Autowired
    public WalletController(WalletService walletService){this.walletService = walletService;}

    @GetMapping()
    public List<WalletDTO> getWallets() {return walletService.getAllWallets();}
}
