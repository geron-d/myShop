package by.it.academy.repositories.connections;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionMySQL implements ConnectionSQL {
    private String url = null;
    private String username = null;
    private String password = null;
    private Connection connection;
    String DB_DRIVER = null;

    public ConnectionMySQL() {
        Properties properties = new Properties();

        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(resourceAsStream);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            DB_DRIVER = properties.getProperty("DB_DRIVER");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConnectionMySQL(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
