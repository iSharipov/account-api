package ru.sbrf.bh.accountapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.bh.accountapi.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
