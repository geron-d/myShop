package by.it.academy.repositories.user;

import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAPIRepository implements UserRepository<User>{
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
    public Optional<User> get(User user) {
        User thisUser;
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                if (resultSet.getInt("id") == user.getId()) {
                    int id = resultSet.getInt("id");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    AccessLevel accessLevel = AccessLevel.valueOf(resultSet.getString("accessLevel"));
                    thisUser = new User(id, login, password, accessLevel);
                    return Optional.of(thisUser);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(User user, User newUser) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE users SET login=?, password=? WHERE id=?");
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getPassword());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
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
}
