package com.sahaplus.baascore.repository;

import com.sahaplus.baascore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
