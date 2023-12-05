package com.setkies.sinp.domain.wallet.service;

import com.setkies.sinp.domain.wallet.Wallet;
import com.setkies.sinp.domain.wallet.repo.WalletRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepo walletRepo;

    public Long addMoney(Long id, Long addMoney){
        Wallet wallet = walletRepo.findById(id).orElseThrow();
        wallet.addMoney(addMoney);

        return wallet.getMoney();
    }
    public Long getMoney(Long id){
        Wallet wallet = walletRepo.findById(id).orElseThrow();

        return wallet.getMoney();
    }
};
