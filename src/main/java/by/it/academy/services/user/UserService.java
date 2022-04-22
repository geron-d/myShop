package by.it.academy.services.user;

import by.it.academy.entities.User;

import java.util.List;

public interface UserService<T> {
    boolean create(T t);

    User get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    User getByLoginPassword(String login, String password);
}
