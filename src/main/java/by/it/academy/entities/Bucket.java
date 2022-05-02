package by.it.academy.entities;

import lombok.Data;

import java.util.Objects;

@Data
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

    public Bucket(int id) {
        this.id = id;
    }

    public Bucket() {
    }
}
