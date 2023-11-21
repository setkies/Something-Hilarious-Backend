package com.setkies.sinp.domain.wallet.repo;

import com.setkies.sinp.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, Long> {
}
