package by.it.academy.repositories.user;

import java.util.List;

public interface UserRepository<T> {
    boolean create(T t);

    T get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAllUsers();

    T getByLoginPassword(String login, String password);

    boolean checkLogin(String login);

    boolean checkUserId(T t);
}
