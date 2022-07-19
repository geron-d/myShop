package by.it.academy.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_in_bucket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInBucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private int amount;

    public ProductInBucket(User user, Product product, int amount) {
        this.user = user;
        this.product = product;
        this.amount = amount;
    }
}
