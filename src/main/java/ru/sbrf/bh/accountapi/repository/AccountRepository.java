package ru.sbrf.bh.accountapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.bh.accountapi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountByAccountNumber(String accountNumber);
}
