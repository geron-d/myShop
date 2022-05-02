package by.it.academy.entities;

import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private int id;
    private String login;
    private String password;
    private AccessLevel accessLevel = AccessLevel.USER;

    public User(int id, String login, String password, AccessLevel accessLevel) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public User(String login, String password, AccessLevel accessLevel) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
