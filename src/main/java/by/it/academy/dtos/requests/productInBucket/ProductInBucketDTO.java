package by.it.academy.dtos.requests.productInBucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * The {@code ProductInBucketDTO} dto represents the {@code ProductInBucket} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInBucketDTO {

    /**
     * User which have this product ib bucket.
     */
    @NotNull
    private User user;

    /**
     * Product which stored in user's bucket.
     */
    @NotNull
    private Product product;

    /**
     * Amount of this product.
     */
    private int amount;

}
