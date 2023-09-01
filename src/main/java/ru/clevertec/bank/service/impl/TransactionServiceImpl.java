package ru.clevertec.bank.service.impl;

import ru.clevertec.bank.dao.AccountRepository;
import ru.clevertec.bank.dao.TransactionRepository;
import ru.clevertec.bank.dao.UserRepository;
import ru.clevertec.bank.logic.OutputLogic;
import ru.clevertec.bank.util.Check;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.model.Transaction;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    UserRepository userRepository;

    public TransactionServiceImpl(){
        transactionRepository = new TransactionRepository();
        accountRepository = new AccountRepository();
        userRepository = new UserRepository();
    }

    @Override
    public void create(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void changeBalance(Transaction transaction, long user_id){
        Account account = transaction.getAccount();
        User sender = userRepository.findById(user_id);
        double balance = account.getBalance();

        account.setBalance(balance + transaction.getPrice());
        accountRepository.updateBalance(account);
        List<String> check = Check.create(transaction, sender);
        OutputLogic.inputInFile(check);

    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void update(Transaction transaction) {
        transactionRepository.update(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public Transaction getById(long id) {
        return transactionRepository.findById(id);
    }
}
