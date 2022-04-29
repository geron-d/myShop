package by.it.academy.repositories.user;

import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository<T> {
    boolean create(T t);

    T get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    T getByLoginPassword(String login, String password);

    boolean checkLogin(String login);
}
