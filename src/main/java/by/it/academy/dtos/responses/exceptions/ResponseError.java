package by.it.academy.dtos.responses.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code ResponseError} dto represents the response error which gives when happen exception.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseError {

    /**
     * A given message when happen exception.
     */
    private String message;

    /**
     * A given error data message when happen exception.
     */
    private String errorData;

}
