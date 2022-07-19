package by.it.academy.services.user;

import by.it.academy.contants.Order;
import by.it.academy.entities.User;
import by.it.academy.repositories.hiber.user.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.service.UserService interface.
 *
 * @author Maxim Zhevnov
 */
public class UserAPIService implements UserService<User> {
    private final Session session;
    private final UserRepository<User> userRepository;

    /**
     * Creates a new {@link UserRepository} to manage objects of the given session.
     *
     * @param session        must not be {@literal null}.
     * @param userRepository must not be {@literal null}.
     */
    public UserAPIService(Session session, UserRepository<User> userRepository) {
        this.session = session;
        this.userRepository = userRepository;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#getUserById
     */
    @Override
    public Optional<User> getUserById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> user = userRepository.getUserById(id);

        transaction.commit();

        return user;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#saveUser
     */
    @Override
    public Optional<User> saveUser(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.saveUser(user);

        transaction.commit();

        return optionalUser;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#deleteUser
     */
    @Override
    public void deleteUser(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        userRepository.deleteUser(user);

        transaction.commit();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#getAllUsers
     */
    @Override
    public List<User> getAllUsers(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<User> users = userRepository.getAllUsers(order);

        transaction.commit();

        return users;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#getUserByLoginPassword
     */
    @Override
    public Optional<User> getUserByLoginPassword(String login, String password) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.getUserByLoginPassword(login, password);

        transaction.commit();

        return optionalUser;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#getUserByLoginPassword
     */
    @Override
    public Optional<User> getUserByLoginPassword(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.getUserByLoginPassword(user);

        transaction.commit();

        return optionalUser;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#getUserByValuableFields
     */
    @Override
    public Optional<User> getUserByValuableFields(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<User> optionalUser = userRepository.getUserByValuableFields(user);

        transaction.commit();

        return optionalUser;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#checkUserLogin
     */
    @Override
    public boolean checkUserLogin(String login) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        boolean checkUserLogin = userRepository.checkUserLogin(login);

        transaction.commit();

        return checkUserLogin;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.service.UserService#checkUserId
     */
    @Override
    public boolean checkUserId(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        boolean checkUserId = userRepository.checkUserId(user);

        transaction.commit();

        return checkUserId;
    }


}
