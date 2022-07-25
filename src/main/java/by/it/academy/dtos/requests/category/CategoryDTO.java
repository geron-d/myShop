package by.it.academy.dtos.requests.category;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * The {@code CategoryDTO} dto represents the {@code Category} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    /**
     * A name of the category of product.
     */
    @NotBlank
    private String name;

}
