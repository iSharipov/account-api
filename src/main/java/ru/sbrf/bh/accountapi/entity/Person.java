package ru.sbrf.bh.accountapi.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSON_ID")
    private Long id;

    private String name;

    @JoinTable(name = "PERSON_ACCOUNT",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    @OneToMany
    private Set<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccount() {
        this.accounts = accounts;
    }
}
