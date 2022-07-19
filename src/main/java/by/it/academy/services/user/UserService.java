package by.it.academy.services.user;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a service for a specific type of user.
 *
 * @param <T> the type of the entity to handle
 * @author Maxim Zhevnov
 */
public interface UserService<T> {

    /**
     * Retrieves a user by its id.
     *
     * @param id must not be less than 1.
     * @return the user with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getUserById(int id);

    /**
     * Saves a given user. Use the returned instance for further operations as the save operation might have
     * changed the user instance completely.
     *
     * @param t must not be {@literal null}.
     * @return the saved user; will never be {@literal null}.
     */
    Optional<T> saveUser(T t);

    /**
     * Deletes a given user.
     *
     * @param t must not be {@literal null}.
     */
    void deleteUser(T t);

    /**
     * Returns all instances of the user in straight or reverse order.
     *
     * @param order must be ASC or DESC.
     * @return all users
     */
    List<T> getAllUsers(Order order);

    /**
     * Retrieves a user by its login and password.
     *
     * @param login    must not be {@literal null}.
     * @param password must not be {@literal null}.
     * @return the user with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getUserByLoginPassword(String login, String password);

    /**
     * Retrieves a user by its login and password.
     *
     * @param t must not be {@literal null}.
     * @return the user with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getUserByLoginPassword(T t);

    /**
     * Retrieves a user by its valuable fields.
     *
     * @param t must not be {@literal null}.
     * @return the user with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getUserByValuableFields(T t);

    /**
     * Retrieves {@literal true} if the given login found.
     *
     * @param login must not be {@literal null}.
     * @return {@literal true} if the given login found or {@literal false} if none found.
     */
    boolean checkUserLogin(String login);

    /**
     * Retrieves {@literal true} if the given user's id found.
     *
     * @param t must not be {@literal null}.
     * @return {@literal true} if the given user's id found or {@literal false} if none found.
     */
    boolean checkUserId(T t);
}
