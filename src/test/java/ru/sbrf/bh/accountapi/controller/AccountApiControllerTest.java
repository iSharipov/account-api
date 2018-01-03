package ru.sbrf.bh.accountapi.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.bh.accountapi.AccountApiApplication;
import ru.sbrf.bh.accountapi.entity.Account;
import ru.sbrf.bh.accountapi.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.sbrf.bh.accountapi.enumeration.OperationStatus.SUCCESS;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = AccountApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@Transactional
public class AccountApiControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private AccountRepository accountRepository;

    @Value("${first.account}")
    private String firstAccount;

    @Value("${second.account}")
    private String secondAccount;

    @Before
    public void setUp() {
        Account account1 = new Account(firstAccount);
        account1.setAmount(new BigDecimal(1000));
        Account account2 = new Account(secondAccount);
        accountRepository.save(Arrays.asList(account1, account2));
    }

    @Test
    public void increaseBalance() throws Exception {
        mvc.perform(post("/api/increase-balance")
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountTo", firstAccount).param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", is(SUCCESS.name())));
    }

    @Test
    public void reduceBalance() throws Exception {
        mvc.perform(post("/api/reduce-balance")
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountFrom", firstAccount).param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", is(SUCCESS.name())));
    }

    @Test
    public void transaction() throws Exception {
        mvc.perform(post("/api/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountTo", secondAccount)
                .param("accountFrom", firstAccount)
                .param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", is(SUCCESS.name())));
    }
}