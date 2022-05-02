package by.it.academy.repositories.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSQL {
    Connection connect() throws ClassNotFoundException, SQLException;
}
