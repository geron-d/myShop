package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductInBucket {
    private Product product;
    private int bucketId;
    private int amount;
}
