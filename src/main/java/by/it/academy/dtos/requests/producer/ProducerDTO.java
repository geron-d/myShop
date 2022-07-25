package by.it.academy.dtos.requests.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * The {@code ProducerDTO} dto represents the {@code Producer} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerDTO {

    /**
     * A name of the producer of product.
     */
    @NotBlank
    private String name;

}
