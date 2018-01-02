package ru.sbrf.bh.accountapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.entity.Account;
import ru.sbrf.bh.accountapi.entity.Transaction;
import ru.sbrf.bh.accountapi.enumeration.OperationType;
import ru.sbrf.bh.accountapi.repository.AccountRepository;
import ru.sbrf.bh.accountapi.repository.TransactionRepository;
import ru.sbrf.bh.accountapi.vo.Params;

import javax.transaction.Transactional;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static ru.sbrf.bh.accountapi.enumeration.OperationStatus.ERROR;
import static ru.sbrf.bh.accountapi.enumeration.OperationStatus.SUCCESS;
import static ru.sbrf.bh.accountapi.enumeration.OperationType.INCREASE;
import static ru.sbrf.bh.accountapi.enumeration.OperationType.REDUCE;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public synchronized TransactionResult increaseBalance(Params params) {
        BigDecimal newAmount;
        TransactionResult result = new TransactionResult();
        result.setStatus(ERROR);
        Account account = null;
        try {
            account = accountRepository.getAccountByAccountNumber(params.getAccountTo());
            if (account != null) {
                newAmount = account.increaseAmount(new BigDecimal(params.getAmount()));
                accountRepository.save(account);
                result.setRenewedAmount(newAmount);
                result.setStatus(SUCCESS);
                result.addMessage("Transaction Successful");
            } else {
                result.addMessage("Account Not Found: " + params.getAccountTo());
            }
        } catch (Exception e) {
            result.addMessage(e.getMessage());
            LOGGER.info(e.getMessage());
        }
        saveTransaction(account, result, INCREASE, params.getAmount());
        return result;
    }

    @Override
    @Transactional
    public synchronized TransactionResult reduceBalance(Params params) {
        BigDecimal newAmount;
        TransactionResult result = new TransactionResult();
        result.setStatus(ERROR);
        Account account = null;
        try {
            account = accountRepository.getAccountByAccountNumber(params.getAccountFrom());
            if (account != null) {
                BigDecimal amount = account.getAmount();
                if (isSufficientBalance(params, amount)) {
                    newAmount = account.substractAmount(new BigDecimal(params.getAmount()));
                    accountRepository.save(account);
                    result.setRenewedAmount(newAmount);
                    result.setStatus(SUCCESS);
                    result.addMessage("Transaction Successful");
                } else {
                    result.addMessage("Debit Sum: " + params.getAmount() + " greater than balance: " + amount);
                }
            } else {
                result.addMessage("Account Not Found: " + params.getAccountFrom());
            }
        } catch (Exception e) {
            result.addMessage(e.getMessage());
            LOGGER.info(e.getMessage());
        }
        saveTransaction(account, result, REDUCE, params.getAmount());
        return result;
    }

    private void saveTransaction(Account account, TransactionResult result, OperationType type, String amount) {
        if (account != null) {
            try {
                transactionRepository.save(new Transaction(account, type, result, amount));
            } catch (Exception e) {
                result.setStatus(ERROR);
                result.addMessage("Error while saving transaction");
            }
        }
    }

    //Фактически этого метода может не быть в API,
    //операции списания и зачисления могут быть вызваны непосредственно из прикладного кода
    @Override
    @Transactional
    public synchronized TransactionResult transfer(Params params) {
        TransactionResult result = new TransactionResult();
        TransactionResult increaseResult = increaseBalance(params);
        TransactionResult reduceResult;
        Set<String> messages = new HashSet<>();
        if (increaseResult != null) {
            if (increaseResult.getStatus() != null && increaseResult.getStatus() == SUCCESS) {
                reduceResult = reduceBalance(params);
                if (reduceResult != null) {
                    if (reduceResult.getStatus() != null && reduceResult.getStatus() == SUCCESS) {
                        result.setStatus(SUCCESS);
                    } else {
                        result.setStatus(ERROR);
                    }
                    messages.addAll(reduceResult.getMessages());
                }
            }
            messages.addAll(increaseResult.getMessages());
        }

        result.setMessages(messages);
        return result;
    }

    private boolean isSufficientBalance(Params params, BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0
                && (amount.compareTo(new BigDecimal(params.getAmount())) > 0
                || amount.compareTo(new BigDecimal(params.getAmount())) == 0);
    }
}