package by.it.academy.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "bucket")
@ToString(exclude = "bucket")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(columnDefinition = "enum('ADMIN','USER')", name = "access_Level")
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel = AccessLevel.USER;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private List<ProductInBucket> bucket = new ArrayList<>();

    public User(String login, String password, AccessLevel accessLevel) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, AccessLevel accessLevel, List<ProductInBucket> bucket) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
        this.bucket = bucket;
    }
}
