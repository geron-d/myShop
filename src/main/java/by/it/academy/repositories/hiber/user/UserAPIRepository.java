package by.it.academy.repositories.hiber.user;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.User;
import by.it.academy.repositories.hiber.category.CategoryAPIRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.repositories.hiber.UserRepository interface.
 *
 * @author Maxim Zhevnov
 */
public class UserAPIRepository implements UserRepository<User> {
    Logger log = Logger.getLogger(UserAPIRepository.class);
    private final Session session;

    /**
     * Creates a new {@link UserRepository} to manage objects of the given session.
     *
     * @param session must not be {@literal null}.
     */
    public UserAPIRepository(Session session) {
        this.session = session;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#getUserById
     */
    @Override
    public Optional<User> getUserById(int id) {
        Optional<User> user = Optional.empty();
        try {
            user = Optional.of(session.get(User.class, id));
            log.info("UserAPIRepository - method: getUserById: " + user);
        } catch (NullPointerException e) {
            log.info("UserAPIRepository - method: getUserById: " + e);
            return user;
        }
        return user;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#saveUser
     */
    @Override
    public Optional<User> saveUser(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);

        if (optionalUser.isPresent()) {
            if ((Objects.isNull(optionalUser.get().getId())
                    || !getUserById(optionalUser.get().getId()).isPresent())
                    && !getUserByValuableFields(optionalUser.get()).isPresent()) {
                session.persist(optionalUser.get());
                log.info("UserAPIRepository - method: saveUser: persist: " + optionalUser);
            } else if (!getUserByValuableFields(optionalUser.get()).isPresent()) {
                session.merge(optionalUser.get());
                log.info("UserAPIRepository - method: saveUser: merge: " + optionalUser);
            } else {
                log.info("UserAPIRepository - method: saveUser: user is already exist");
            }
        } else {
            log.info("UserAPIRepository - method: saveUser: user is null");
        }

        return getUserByValuableFields(user);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#deleteUser
     */
    @Override
    public void deleteUser(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);

        if (optionalUser.isPresent() && Objects.nonNull(optionalUser.get().getId())) {
            session.delete(optionalUser.get());
            log.info("UserAPIRepository - method: deleteUser: remove: " + optionalUser);
        } else {
            log.info("UserAPIRepository - method: deleteUser: user doesn't exist");
        }
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#getAllUsers
     */
    @Override
    public List<User> getAllUsers(Order order) {
        List<User> users;

        TypedQuery<User> query = session.createQuery(HQL.GET_ALL_USERS_HQL, User.class);
        TypedQuery<User> queryDesc = session.createQuery(HQL.GET_ALL_USERS_DESC_HQL, User.class);
        users = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("UserAPIRepository - method: getAllUsers: " + users);

        return users;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#getUserByLoginPassword
     */
    @Override
    public Optional<User> getUserByLoginPassword(String login, String password) {
        Optional<User> user = Optional.empty();
        try {
            TypedQuery<User> query = session.createQuery(HQL.GET_USER_BY_LOGIN_PASSWORD_HQL, User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            user = Optional.of(query.getSingleResult());
            log.info("UserAPIRepository - method: getUserByLoginPassword: " + user);
        } catch (NoResultException e) {
            log.info("UserAPIRepository - method: getUserByLoginPassword: " + e);
            return user;
        }
        return user;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#getUserByLoginPassword
     */
    @Override
    public Optional<User> getUserByLoginPassword(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isPresent()) {
            try {
                TypedQuery<User> query = session.createQuery(HQL.GET_USER_BY_LOGIN_PASSWORD_HQL, User.class);
                query.setParameter("login", optionalUser.get().getLogin());
                query.setParameter("password", optionalUser.get().getPassword());
                optionalUser = Optional.of(query.getSingleResult());
                log.info("UserAPIRepository - method: getUserByLoginPassword: " + optionalUser);
            } catch (NoResultException e) {
                log.info("UserAPIRepository - method: getUserByLoginPassword: " + e);
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#getUserByValuableFields
     */
    @Override
    public Optional<User> getUserByValuableFields(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isPresent()) {
            return getUserByLoginPassword(optionalUser.get());
        } else {
            return optionalUser;
        }
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#checkUserLogin
     */
    @Override
    public boolean checkUserLogin(String login) {
        Optional<User> optionalUser;
        try {
            TypedQuery<User> query = session.createQuery(HQL.GET_USER_BY_LOGIN_HQL, User.class);
            query.setParameter("login", login);
            optionalUser = Optional.of(query.getSingleResult());
            log.info("UserAPIRepository - method: checkUserLogin: " + optionalUser);
        } catch (NoResultException e) {
            log.info("UserAPIRepository - method: checkUserLogin: " + e);
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.UserRepository#checkUserId
     */
    @Override
    public boolean checkUserId(User user) {
        Optional<User> optionalUser = Optional.ofNullable(user);
        if (optionalUser.isPresent() && Objects.nonNull(user.getId())) {
            try {
                optionalUser = Optional.of(session.get(User.class, user.getId()));
                log.info("UserAPIRepository - method: checkUserId: " + optionalUser);
            } catch (NullPointerException e) {
                log.info("UserAPIRepository - method: checkUserId: " + e);
                return false;
            }
        } else {
            log.info("UserAPIRepository - method: checkUserId: " + optionalUser);
            return false;
        }

        return true;
    }
}
