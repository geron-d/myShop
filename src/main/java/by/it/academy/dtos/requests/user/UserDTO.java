package by.it.academy.dtos.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * The {@code User} class represents the {@code User} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * A login of user.
     */
    @NotBlank
    private String login;

    /**
     * A password of user.
     */
    @NotBlank
    private String password;

}
