package by.it.academy.dtos.requests.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The {@code ProductDecreaseRequest} request represents the {@code Product} class when it needs be decreased.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDecreaseRequest {

    /**
     * An identifier of {@code Product}.
     */
    @NotNull
    private Long productId;

    /**
     * Decreased amount of product.
     */
    private int amount;

}
