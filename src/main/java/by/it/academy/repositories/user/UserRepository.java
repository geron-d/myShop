package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

    User findUserByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

}
