package ru.clevertec.bank.service;

import ru.clevertec.bank.model.Bank;

import java.util.List;

public interface BankService {

    public void create(Bank bank);

    public List<Bank> getAll();

    public void update(Bank bank);

    public void delete(Bank bank);

    public Bank getById(long id);
}
