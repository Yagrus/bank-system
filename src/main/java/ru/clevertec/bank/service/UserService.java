package ru.clevertec.bank.service;

import ru.clevertec.bank.model.User;

import java.util.List;

/**
 * The UserService interface represents the service for user-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on users.
 */
public interface UserService {

    /**
     * Creates a new user with the specified details.
     *
     * @param user The User object containing the details of the user to be created.
     */
    public void create(User user);

    /**
     * Retrieves list of users from database.
     *
     * @return The list of users.
     */
    public List<User> getAll();

    /**
     * Update old user with the new specified details.
     *
     * @param user The Comment object containing the details of the user to be updated.
     */
    public void update(User user);

    /**
     * Delete comment with the specified ID.
     *
     * @param user The User object containing the details of the user to be deleted.
     */
    public void delete(User user);

    /**
     * Retrieves a user with the specified ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     */
    public User getById(long id);
    
}
