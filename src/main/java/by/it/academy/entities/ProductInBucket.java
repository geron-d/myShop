package by.it.academy.entities;

import java.util.Objects;

public class ProductInBucket {
    private Product product;
    private int amount = 1;

    public ProductInBucket(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public ProductInBucket(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        return amount == that.amount && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, amount);
    }

    @Override
    public String toString() {
        return "ProductInBucket{" +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }
}
