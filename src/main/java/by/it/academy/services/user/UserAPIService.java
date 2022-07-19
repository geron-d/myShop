package by.it.academy.services.user;

import by.it.academy.contants.Order;
import by.it.academy.entities.User;
import by.it.academy.repositories.hiber.user.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserAPIService implements UserService<User> {
    private final Session session;
    private final UserRepository<User> userRepository;

    public UserAPIService(Session session, UserRepository<User> userRepository) {
        this.session = session;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> user = userRepository.getUserById(id);

        transaction.commit();

        return user;
    }

    @Override
    public Optional<User> saveUser(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.saveUser(user);

        transaction.commit();

        return optionalUser;
    }

    @Override
    public void deleteUser(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        userRepository.deleteUser(user);

        transaction.commit();
    }

    @Override
    public List<User> getAllUsers(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<User> users = userRepository.getAllUsers(order);

        transaction.commit();

        return users;
    }

    @Override
    public Optional<User> getUserByLoginPassword(String login, String password) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.getUserByLoginPassword(login, password);

        transaction.commit();

        return optionalUser;
    }

    @Override
    public Optional<User> getUserByLoginPassword(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.getUserByLoginPassword(user);

        transaction.commit();

        return optionalUser;
    }

    @Override
    public Optional<User> getUserByValuableFields(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.getUserByValuableFields(user);

        transaction.commit();

        return optionalUser;
    }

    @Override
    public boolean checkUserLogin(String login) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        boolean checkUserLogin = userRepository.checkUserLogin(login);

        transaction.commit();

        return checkUserLogin;
    }

    @Override
    public boolean checkUserId(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        boolean checkUserId = userRepository.checkUserId(user);

        transaction.commit();

        return checkUserId;
    }


}
