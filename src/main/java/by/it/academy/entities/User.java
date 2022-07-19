package by.it.academy.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code User} class represents the user.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "bucket")
@ToString(exclude = "bucket")
public class User {

    /**
     * An identifier of {@code User}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * A login of user.
     */
    @Column(name = "login")
    private String login;

    /**
     * A password of user.
     */
    @Column(name = "password")
    private String password;

    /**
     * Access level of user.
     */
    @Column(columnDefinition = "enum('ADMIN','USER')", name = "access_Level")
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel = AccessLevel.USER;

    /**
     * Bucket where stored products which user want to buy.
     */
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private List<ProductInBucket> bucket = new ArrayList<>();

    /**
     * Initializes a newly created {@code User} object so that it represents
     * the user
     *
     * @param login       A {@code String}
     * @param password    A {@code String}
     * @param accessLevel An {@code AccessLevel}
     */
    public User(String login, String password, AccessLevel accessLevel) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    /**
     * Initializes a newly created {@code User} object so that it represents
     * the user
     *
     * @param login    A {@code String}
     * @param password A {@code String}
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Initializes a newly created {@code User} object so that it represents
     * the user
     *
     * @param login       A {@code String}
     * @param password    A {@code String}
     * @param accessLevel An {@code AccessLevel}
     * @param bucket      A {@code List<ProductInBucket>}
     */
    public User(String login, String password, AccessLevel accessLevel, List<ProductInBucket> bucket) {
        this.login = login;
        this.password = password;
        this.accessLevel = accessLevel;
        this.bucket = bucket;
    }
}
