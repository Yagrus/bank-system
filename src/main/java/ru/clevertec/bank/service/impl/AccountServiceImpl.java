package ru.clevertec.bank.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.clevertec.bank.aop.annotation.Logging;
import ru.clevertec.bank.dao.AccountRepository;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.service.AccountService;
import ru.clevertec.bank.util.Iban;

import java.util.List;

@Logging
public class AccountServiceImpl implements AccountService {

    AccountRepository repository;

    public AccountServiceImpl(){
        repository = new AccountRepository();
    }

    @Override
    public void create(Account account) {
        account.setIban(Iban.create(account));
        repository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Account account) {
        repository.update(account);
    }

    @Override
    public void delete(Account account) {
        repository.delete(account);
    }

    @Override
    public Account getById(long id) {
        return repository.findById(id);
    }
}
