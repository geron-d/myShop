package by.it.academy.entities;

import java.util.Objects;

public class ProductInBucket {
    private Product product;
    private int bucketId;
    private int amount = 1;

    public ProductInBucket(Product product, int bucketId, int amount) {
        this.product = product;
        this.bucketId = bucketId;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInBucket that = (ProductInBucket) o;
        return bucketId == that.bucketId && amount == that.amount && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, bucketId, amount);
    }

    @Override
    public String toString() {
        return "ProductInBucket{" +
                "product=" + product +
                ", bucketId=" + bucketId +
                ", amount=" + amount +
                '}';
    }
}
