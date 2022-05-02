package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;

import java.util.List;

public class UserAPIService implements UserService<User> {
    private final UserRepository<User> repository;

    public UserAPIService(UserRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(User user) {
        return repository.create(user);
    }

    @Override
    public User get(User user) {
        return repository.get(user);
    }

    @Override
    public boolean update(User user, User newUser) {
        return repository.update(user, newUser);
    }

    @Override
    public boolean delete(User user) {
        return repository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getByLoginPassword(String login, String password) {
        return repository.getByLoginPassword(login, password);
    }

    @Override
    public boolean checkLogin(String login) {
        return repository.checkLogin(login);
    }

}
