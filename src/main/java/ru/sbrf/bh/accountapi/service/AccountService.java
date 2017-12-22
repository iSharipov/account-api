package ru.sbrf.bh.accountapi.service;

import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.vo.Transaction;

public interface AccountService {
    TransactionResult increaseBalance(Transaction transaction);

    TransactionResult reduceBalance(Transaction transaction);

    TransactionResult accountToAccountTransaction(Transaction transaction);
}
