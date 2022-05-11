package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private AccessLevel accessLevel = AccessLevel.USER;

    public User(String login, String password, AccessLevel accessLevel) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
