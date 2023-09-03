package ru.clevertec.bank.service;

import ru.clevertec.bank.model.Transaction;

import java.util.List;

/**
 * The TransactionService interface represents the service for transaction-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on transactions.
 */
public interface TransactionService {

    /**
     * Creates a new transaction and change balance in account.
     *
     * @param transaction The Transaction object containing the details of the transaction to be created.
     * @param user_id Contain id user
     */
    public void changeBalance(Transaction transaction, long user_id);

    /**
     * Creates a new transaction with the specified details.
     *
     * @param transaction The Transaction object containing the details of the transaction to be created.
     */
    public void create(Transaction transaction);

    /**
     * Retrieves list of transactions from database.
     *
     * @return The list of transactions.
     */
    public List<Transaction> getAll();

    /**
     * Update old transaction with the new specified details.
     *
     * @param transaction The Comment object containing the details of the transaction to be updated.
     */
    public void update(Transaction transaction);

    /**
     * Delete comment with the specified ID.
     *
     * @param transaction The Transaction object containing the details of the transaction to be deleted.
     */
    public void delete(Transaction transaction);

    /**
     * Retrieves a transaction with the specified ID.
     *
     * @param id The ID of the transaction to retrieve.
     * @return The Transaction object with the specified ID.
     */
    public Transaction getById(long id);
}
