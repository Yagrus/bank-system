package ru.clevertec.bank.service;

import ru.clevertec.bank.model.Account;

import java.util.List;

/**
 * The AccountService interface represents the service for account-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on accounts.
 */
public interface AccountService {

    /**
     * Creates a new account with the specified details.
     *
     * @param account The Account object containing the details of the account to be created.
     */
    public void create(Account account);

    /**
     * Retrieves list of accounts from database.
     *
     * @return The list of accounts.
     */
    public List<Account> getAll();

    /**
     * Update old account with the new specified details.
     *
     * @param account The Comment object containing the details of the account to be updated.
     */
    public void update(Account account);

    /**
     * Delete comment with the specified ID.
     *
     * @param account The Account object containing the details of the account to be deleted.
     */
    public void delete(Account account);

    /**
     * Retrieves an account with the specified ID.
     *
     * @param id The ID of the account to retrieve.
     * @return The Account object with the specified ID.
     */
    public Account getById(long id);
}
