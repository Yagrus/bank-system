package ru.clevertec.bank.service.impl;

import ru.clevertec.bank.dao.Repository;
import ru.clevertec.bank.dao.impl.TransactionRepository;
import ru.clevertec.bank.model.Transaction;
import ru.clevertec.bank.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    Repository<Transaction> repository;

    public TransactionServiceImpl(){
        repository = new TransactionRepository();
    }

    @Override
    public void create(Transaction transaction) {
        repository.save(transaction);
    }

    public void replenishBalance(){

    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Transaction transaction) {
        repository.update(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        repository.delete(transaction);
    }

    @Override
    public Transaction getById(long id) {
        return repository.findById(id);
    }
}
