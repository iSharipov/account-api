package ru.sbrf.bh.accountapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.entity.Account;
import ru.sbrf.bh.accountapi.entity.Person;
import ru.sbrf.bh.accountapi.repository.AccountRepository;
import ru.sbrf.bh.accountapi.repository.PersonRepository;
import ru.sbrf.bh.accountapi.vo.Transaction;

import javax.transaction.Transactional;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

import static ru.sbrf.bh.accountapi.dto.TransactionResult.Status.ERROR;
import static ru.sbrf.bh.accountapi.dto.TransactionResult.Status.SUCCESS;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public TransactionResult increaseBalance(Transaction transaction) {
        BigDecimal newAmount;
        TransactionResult result = new TransactionResult();
        result.setStatus(ERROR);
        result.setMessage("Transaction Failed");
        try {
            Person person = personRepository.getOne(Long.valueOf(transaction.getPersonIdTo()));
            Account account = findAccountByPersonAndAccountNumber(person, transaction.getAccountNumberTo());
            if (account != null) {
                newAmount = account.increaseAmount(new BigDecimal(transaction.getTransactionAmount()));
                accountRepository.save(account);
                result.setRenewedAmount(newAmount);
                result.setStatus(SUCCESS);
                result.setMessage("Transaction Successful");
            } else {
                result.setMessage("Account Not Found: " + transaction.getAccountNumberTo());
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            LOGGER.info(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public TransactionResult reduceBalance(Transaction transaction) {
        BigDecimal newAmount;
        TransactionResult result = new TransactionResult();
        result.setStatus(ERROR);
        result.setMessage("Transaction Failed");
        try {
            Person person = personRepository.getOne(Long.valueOf(transaction.getPersonIdFrom()));
            Account account = findAccountByPersonAndAccountNumber(person, transaction.getAccountNumberFrom());
            if (account != null) {
                BigDecimal amount = account.getAmount();
                if (isSufficientBalance(transaction, amount)) {
                    newAmount = account.substractAmount(new BigDecimal(transaction.getTransactionAmount()));
                    accountRepository.save(account);
                    result.setRenewedAmount(newAmount);
                    result.setStatus(SUCCESS);
                    result.setMessage("Transaction Successful");
                } else {
                    result.setMessage("Transaction Sum: " + transaction.getTransactionAmount() + " greater than balance: " + amount);
                }
            } else {
                result.setMessage("Account Not Found: " + transaction.getAccountNumberFrom());
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            LOGGER.info(e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public TransactionResult accountToAccountTransaction(Transaction transaction) {
        TransactionResult result = new TransactionResult();
        result.setStatus(ERROR);
        try {
            Person person = personRepository.getOne(Long.valueOf(transaction.getPersonIdFrom()));
            Account accountFrom = findAccountByPersonAndAccountNumber(person, transaction.getAccountNumberFrom());
            Account accountTo = accountRepository.getAccountByAccountNumber(transaction.getAccountNumberTo());
            if (accountFrom != null && accountTo != null) {
                BigDecimal amount = accountFrom.getAmount();
                if (isSufficientBalance(transaction, amount)) {
                    accountTo.increaseAmount(new BigDecimal(transaction.getTransactionAmount()));
                    accountFrom.substractAmount(new BigDecimal(transaction.getTransactionAmount()));
                    accountRepository.save(Arrays.asList(accountFrom, accountTo));
                    result.setStatus(SUCCESS);
                    result.setMessage("Transaction Successfull");
                } else {
                    result.setMessage("Transaction Sum: " + transaction.getTransactionAmount() + " greater than balance: " + amount);
                }
            } else {
                result.setMessage("AccountNumberFrom: " + accountFrom + " " + "AccountNumberTo: " + accountTo);
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            LOGGER.info(e.getMessage());
        }
        return result;
    }

    private boolean isSufficientBalance(Transaction transaction, BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0
                && (amount.compareTo(new BigDecimal(transaction.getTransactionAmount())) > 0
                || amount.compareTo(new BigDecimal(transaction.getTransactionAmount())) == 0);
    }

    private Account findAccountByPersonAndAccountNumber(Person person, String accountNumber) {
        Set<Account> accounts = person.getAccounts();
        if (accounts != null && !accounts.isEmpty()) {
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        return null;
    }
}