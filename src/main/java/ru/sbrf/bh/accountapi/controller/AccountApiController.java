package ru.sbrf.bh.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.service.AccountService;
import ru.sbrf.bh.accountapi.vo.Transaction;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AccountApiController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/increase-balance", method = POST)
    public TransactionResult increaseBalance(
            @RequestParam(value = "personIdTo") String personIdTo,
            @RequestParam(value = "accountNumberTo") String accountNumberTo,
            @RequestParam(value = "sum") String sum) {
        return accountService.increaseBalance(new Transaction.Builder()
                .withPersonIdTo(personIdTo)
                .withAccountNumberTo(accountNumberTo)
                .withTransactionAmount(sum).build());
    }

    @RequestMapping(value = "/reduce-balance", method = POST)
    public TransactionResult reduceBalance(
            @RequestParam(value = "personIdFrom") String personIdFrom,
            @RequestParam(value = "accountNumberFrom") String accountNumberFrom,
            @RequestParam(value = "sum") String sum) {
        return accountService.reduceBalance(new Transaction.Builder()
                .withPersonIdFrom(personIdFrom)
                .withAccountNumberFrom(accountNumberFrom)
                .withTransactionAmount(sum).build());
    }

    @RequestMapping(value = "/transaction", method = POST)
    public TransactionResult transaction(
            @RequestParam(value = "personIdFrom") String personIdFrom,
            @RequestParam(value = "accountNumberFrom") String accountNumberFrom,
            @RequestParam(value = "accountNumberTo") String accountNumberTo,
            @RequestParam(value = "sum") String sum) {
        return accountService.accountToAccountTransaction(new Transaction.Builder()
                .withPersonIdFrom(personIdFrom)
                .withAccountNumberFrom(accountNumberFrom)
                .withAccountNumberTo(accountNumberTo)
                .withTransactionAmount(sum).build());
    }
}