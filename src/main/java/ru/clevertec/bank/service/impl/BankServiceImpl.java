package ru.clevertec.bank.service.impl;

import ru.clevertec.bank.aop.annotation.Logging;
import ru.clevertec.bank.dao.BankRepository;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.service.BankService;

import java.util.List;

@Logging
public class BankServiceImpl implements BankService {

    BankRepository repository;

    public BankServiceImpl(){
        repository = new BankRepository();
    }

    @Override
    public void create(Bank bank) {
        repository.save(bank);
    }

    @Override
    public List<Bank> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Bank bank) {
        repository.update(bank);
    }

    @Override
    public void delete(Bank bank) {
        repository.delete(bank);
    }

    @Override
    public Bank getById(long id) {
        return repository.findById(id);
    }
}
