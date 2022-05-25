package by.it.academy.repositories.user;

import by.it.academy.contants.SQL;
import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionSQL;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAPIRepository implements UserRepository<User> {
    Logger log = Logger.getLogger(UserAPIRepository.class);
    private final ConnectionSQL connection;

    public UserAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_INSERT_SQL);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, (user.getAccessLevel().ordinal() + 1));
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: create: " + e);
            return false;
        }
    }

    @Override
    public Optional<User> get(User user) {
        Optional<User> thisUser = Optional.of(new User());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_GET_SQL);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                thisUser = Optional.ofNullable(User.builder()
                        .id(id)
                        .login(login)
                        .password(password)
                        .accessLevel(accessLevel)
                        .build());
                return thisUser;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: get: " + e);
            return thisUser;
        }
        return thisUser;
    }

    @Override
    public boolean update(User user, User newUser) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_UPDATE_SQL);
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getPassword());
            statement.setInt(3, (user.getAccessLevel().ordinal() + 1));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: update: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_DELETE_SQL);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: delete: " + e);
            return false;
        }
    }

    @Override
    public List<Optional<User>> getAllUsers() {
        List<Optional<User>> users = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_GET_ALL_USERS_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                users.add(Optional.ofNullable(User.builder()
                        .id(id)
                        .login(login)
                        .password(password)
                        .accessLevel(accessLevel)
                        .build()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: getAllUsers: " + e);
            return users;
        }
        return users;
    }

    @Override
    public Optional<User> getByLoginPassword(String login, String password) {
        Optional<User> thisUser = Optional.of(new User());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_GET_BY_LOGIN_PASSWORD_SQL);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String thisLogin = resultSet.getString("login");
                String thisPassword = resultSet.getString("password");
                AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                thisUser = Optional.ofNullable(User.builder()
                        .id(id)
                        .login(thisLogin)
                        .password(thisPassword)
                        .accessLevel(accessLevel)
                        .build());
                return thisUser;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: getByLoginPassword: " + e);
            return thisUser;
        }
        return thisUser;
    }

    public boolean checkLogin(String login) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_CHECK_LOGIN_SQL);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: checkLogin: " + e);
            return false;
        }
        return false;
    }

    public boolean checkUserId(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.USER_CHECK_USER_ID_SQL);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("UserAPIRepository - method: checkLogin: " + e);
            return false;
        }
        return false;
    }
}
