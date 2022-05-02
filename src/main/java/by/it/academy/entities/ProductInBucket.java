package by.it.academy.entities;

import lombok.Data;

@Data
public class ProductInBucket {
    private Product product;
    private int bucketId;
    private int amount;

    public ProductInBucket(Product product, int bucketId, int amount) {
        this.product = product;
        this.bucketId = bucketId;
        this.amount = amount;
    }
}
