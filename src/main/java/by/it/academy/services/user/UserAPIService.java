package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return repository.get(user).isPresent()
                ? repository.get(user).get()
                : new User();
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
    public List<User> getAllUsers() {
        return repository.getAllUsers()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public User getByLoginPassword(String login, String password) {
        return repository.getByLoginPassword(login, password).isPresent()
                ? repository.getByLoginPassword(login, password).get()
                : new User();
    }

    @Override
    public boolean checkLogin(String login) {
        return repository.checkLogin(login);
    }

    @Override
    public boolean checkUserId(User user) {
        return repository.checkUserId(user);
    }

}
