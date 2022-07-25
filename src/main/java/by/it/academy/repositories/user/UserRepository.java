package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface for generic operations on a repository for a user.
 *
 * @author Maxim Zhevnov
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Return a user by its login.
     *
     * @return the user with the given login or throw NoSUchElementException if category not found.
     */
    Optional<User> findUserByLogin(String login);

    /**
     * Return a user by its login and password.
     *
     * @return the user by its login and password or throw NoSUchElementException if category not found.
     */
    Optional<User> findUserByLoginAndPassword(String login, String password);

    /**
     * Check user by its login.
     *
     * @return true if such user exists and false when does not.
     */
    boolean existsByLogin(String login);

}
