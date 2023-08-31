package ru.clevertec.bank.service;

import ru.clevertec.bank.model.Account;

import java.util.List;

public interface AccountService {

    public void create(Account account);

    public List<Account> getAll();

    public void update(Account account);

    public void delete(Account account);

    public Account getById(long id);
}
