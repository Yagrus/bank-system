package ru.clevertec.bank.service;

import ru.clevertec.bank.model.Bank;

import java.util.List;

/**
 * The BankService interface represents the service for bank-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on banks.
 */
public interface BankService {

    /**
     * Creates a new bank with the specified details.
     *
     * @param bank The Bank object containing the details of the bank to be created.
     */
    public void create(Bank bank);

    /**
     * Retrieves list of banks from database.
     *
     * @return The list of banks.
     */
    public List<Bank> getAll();

    /**
     * Update old bank with the new specified details.
     *
     * @param bank The Comment object containing the details of the bank to be updated.
     */
    public void update(Bank bank);

    /**
     * Delete comment with the specified ID.
     *
     * @param bank The Bank object containing the details of the bank to be deleted.
     */
    public void delete(Bank bank);

    /**
     * Retrieves a bank with the specified ID.
     *
     * @param id The ID of the bank to retrieve.
     * @return The Bank object with the specified ID.
     */
    public Bank getById(long id);
}
