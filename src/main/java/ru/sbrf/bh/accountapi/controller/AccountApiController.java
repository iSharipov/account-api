package ru.sbrf.bh.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.bh.accountapi.dto.TransactionResult;
import ru.sbrf.bh.accountapi.service.AccountService;
import ru.sbrf.bh.accountapi.vo.Params;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class AccountApiController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/increase-balance", method = POST)
    public TransactionResult increaseBalance(
            @RequestParam(value = "accountTo") String accountTo,
            @RequestParam(value = "amount") String amount) {
        return accountService.increaseBalance(new Params.Builder()
                .withAccountTo(accountTo)
                .withAmount(amount)
                .build());
    }

    @RequestMapping(value = "/reduce-balance", method = POST)
    public TransactionResult reduceBalance(
            @RequestParam(value = "accountFrom") String accountFrom,
            @RequestParam(value = "amount") String amount) {
        return accountService.reduceBalance(new Params.Builder()
                .withAccountFrom(accountFrom)
                .withAmount(amount).build());
    }

    @RequestMapping(value = "/transaction", method = POST)
    public TransactionResult transaction(
            @RequestParam(value = "accountFrom") String accountFrom,
            @RequestParam(value = "accountTo") String accountTo,
            @RequestParam(value = "amount") String amount) {
        return accountService.accountToAccountTransaction(new Params.Builder()
                .withAccountFrom(accountFrom)
                .withAccountTo(accountTo)
                .withAmount(amount).build());
    }
}