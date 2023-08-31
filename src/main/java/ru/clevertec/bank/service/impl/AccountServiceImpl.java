package ru.clevertec.bank.service.impl;

import ru.clevertec.bank.dao.Repository;
import ru.clevertec.bank.dao.impl.AccountRepository;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    Repository<Account> repository;

    public AccountServiceImpl(){
        repository = new AccountRepository();
    }

    @Override
    public void create(Account account) {
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
