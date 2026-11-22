package com.native2.unknown.domain.wallet.repository;

import com.native2.unknown.domain.wallet.entity.CoinWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinWalletRepository extends JpaRepository<CoinWallet, Long> {
}
