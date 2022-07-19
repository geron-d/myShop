package by.it.academy.repositories.hiber.user;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface UserRepository<T> {

    Optional<T> getUserById(int id);

    Optional<T> saveUser(T t);

    void deleteUser(T t);

    List<T> getAllUsers(Order order);

    Optional<T> getUserByLoginPassword(String login, String password);

    Optional<T> getUserByLoginPassword(T t);

    Optional<T> getUserByValuableFields(T t);

    boolean checkUserLogin(String login);

    boolean checkUserId(T t);
}
