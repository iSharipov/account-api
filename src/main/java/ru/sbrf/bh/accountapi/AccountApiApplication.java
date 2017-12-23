package ru.sbrf.bh.accountapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Configuration
@ComponentScan({"ru.sbrf.bh.accountapi.controller", "ru.sbrf.bh.accountapi.service"})
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories("ru.sbrf.bh.accountapi.repository")
@EntityScan("ru.sbrf.bh.accountapi.entity")
public class AccountApiApplication implements CommandLineRunner {

//    @Autowired
//    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) {
//        Account account1 = new Account("123");
//        account1.increaseAmount(new BigDecimal(1000));
//        Account account2 = new Account("124");
//        accountRepository.save(Arrays.asList(account1, account2));
    }
}