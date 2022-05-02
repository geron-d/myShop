package by.it.academy.repositories.user;

import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAPIRepository implements UserRepository<User> {
    private final ConnectionSQL connection;

    public UserAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO users (login, password, accessLevel) VALUES (?,?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, (user.getAccessLevel().ordinal() + 1));
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public User get(User user) {
        User thisUser = new User();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users " +
                    "WHERE id = ?");
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                thisUser = new User(id, login, password, accessLevel);
                return thisUser;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return thisUser;
        }
        return thisUser;
    }

    @Override
    public boolean update(User user, User newUser) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE users SET login=?, password=?, accessLevel=? " +
                    "WHERE id=?");
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getPassword());
            statement.setInt(3, (user.getAccessLevel().ordinal() + 1));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM users " +
                    "WHERE id=?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                users.add(new User(id, login, password, accessLevel));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getByLoginPassword(String login, String password) {
        User thisUser = new User();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users " +
                    "WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String thisLogin = resultSet.getString("login");
                String thisPassword = resultSet.getString("password");
                AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                thisUser = new User(id, thisLogin, thisPassword, accessLevel);
                return thisUser;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return thisUser;
        }
        return thisUser;
    }

    public boolean checkLogin(String login) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users " +
                    "WHERE login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return false;
    }
}
