package by.it.academy.dtos.requests.productInBucket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInBucketWithIdDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    private int amount;

}
