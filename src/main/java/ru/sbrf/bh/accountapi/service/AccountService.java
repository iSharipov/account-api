package ru.sbrf.bh.accountapi.service;

import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.vo.Params;

public interface AccountService {
    TransactionResult increaseBalance(Params params);

    TransactionResult reduceBalance(Params params);

    TransactionResult accountToAccountTransaction(Params params);
}
