package by.it.academy.entities;

import lombok.Data;

import java.util.Objects;

@Data
public class ProductInBucket {
    private Product product;
    private int bucketId;
    private int amount = 1;

    public ProductInBucket(Product product, int bucketId, int amount) {
        this.product = product;
        this.bucketId = bucketId;
        this.amount = amount;
    }
}
