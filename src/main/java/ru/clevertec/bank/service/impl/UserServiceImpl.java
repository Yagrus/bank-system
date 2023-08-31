package ru.clevertec.bank.service.impl;

import ru.clevertec.bank.dao.Repository;
import ru.clevertec.bank.dao.impl.UserRepository;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    Repository<User> repository;

    public UserServiceImpl(){
        repository = new UserRepository();
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(User user) {
        repository.update(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public User getById(long id) {
        return repository.findById(id);
    }
}
