package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bucket {
    private int id;
    private int userId;
    private int productId;
    private int amount = 0;

    public Bucket(int userId, int productId, int amount) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public Bucket(int id) {
        this.id = id;
    }

}
