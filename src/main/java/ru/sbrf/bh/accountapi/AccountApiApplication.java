package ru.sbrf.bh.accountapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sbrf.bh.accountapi.entity.Account;
import ru.sbrf.bh.accountapi.repository.AccountRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class AccountApiApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) {
        Account account1 = new Account("123");
        account1.increaseAmount(new BigDecimal(1000));
        Account account2 = new Account("124");
        accountRepository.save(Arrays.asList(account1, account2));
    }
}