package ru.clevertec.bank.service;

import ru.clevertec.bank.model.Transaction;

import java.util.List;

public interface TransactionService {

    public void changeBalance(Transaction transaction, long user_id);

    public void create(Transaction transaction);

    public List<Transaction> getAll();

    public void update(Transaction transaction);

    public void delete(Transaction transaction);

    public Transaction getById(long id);
}
