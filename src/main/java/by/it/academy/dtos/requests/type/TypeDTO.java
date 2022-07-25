package by.it.academy.dtos.requests.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * The {@code TypeDTO} class represents the {@code Type} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeDTO {

    /**
     * A name of the type of product.
     */
    @NotBlank
    private String name;

}
