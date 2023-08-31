package ru.clevertec.bank.service;

import ru.clevertec.bank.model.User;

import java.util.List;

public interface UserService {

    public void create(User user);
    
    public List<User> getAll();
    
    public void update(User user);
    
    public void delete(User user);
    
    public User getById(long id);
    
}
