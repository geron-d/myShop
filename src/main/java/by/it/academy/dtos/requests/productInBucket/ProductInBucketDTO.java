package by.it.academy.dtos.requests.productInBucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInBucketDTO {

    @NotNull
    private User user;

    @NotNull
    private Product product;

    private int amount;

}
