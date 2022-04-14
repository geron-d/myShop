package by.it.academy.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSQL {
    Connection connect() throws ClassNotFoundException, SQLException;

    boolean close() throws SQLException;
}
