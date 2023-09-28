package com.Spendify.Spendify.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wallets")
public class WalletController {
    private final WalletService walletService;
    @Autowired
    public WalletController(WalletService walletService){this.walletService = walletService;}

    @GetMapping()
    public List<WalletDTO> getWallets() {return walletService.getAllWallets();}
    @GetMapping("{walletId}")
    public WalletDTO getWallet(@PathVariable("walletId") Long walletId ) {return walletService.getWallet(walletId);}
    @DeleteMapping("{walletId}")
    public void deleteWallet(@PathVariable("walletId") Long walletId){walletService.deleteWallet(walletId);}
    @PatchMapping("{walletId}")
    public void updateWallet(@PathVariable("walletId") Long walletId, @RequestBody WalletUpdateRequest walletUpdateRequest){walletService.updateWallet(walletId, walletUpdateRequest);}
    @PostMapping()
    public void addWallet(@RequestBody WalletAddRequest walletAddRequest){walletService.addWallet(walletAddRequest);}
}
