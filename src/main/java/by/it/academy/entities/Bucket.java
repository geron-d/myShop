package by.it.academy.entities;

import java.util.Objects;

public class Bucket {
    private int id;
    private int userId;
    private int productId;
    private int amount = 0;

    public Bucket(int id, int userId, int productId, int amount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public Bucket(int userId, int productId, int amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public Bucket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        Bucket bucket = (Bucket) o;
        return id == bucket.id && userId == bucket.userId && productId == bucket.productId && amount == bucket.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, amount);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
