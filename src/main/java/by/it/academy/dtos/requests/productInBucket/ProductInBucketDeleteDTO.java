package by.it.academy.dtos.requests.productInBucket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The {@code ProductInBucketDeleteDTO} dto represents the deleted {@code ProductInBucket} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInBucketDeleteDTO {

    /**
     * An identifier of {@code User}.
     */
    @NotNull
    private Long userId;

    /**
     * An identifier of {@code Product}.
     */
    @NotNull
    private Long productId;

}
