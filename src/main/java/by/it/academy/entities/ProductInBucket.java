package by.it.academy.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The {@code ProductInBucket} class represents the product in bucket of user.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Entity
@Table(name = "product_in_bucket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInBucket {

    /**
     * An identifier of {@code ProductInBucket}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * User which have this product ib bucket.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    /**
     * Product which stored in user's bucket.
     */
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Amount of this product in user's bucket.
     */
    @Column(name = "amount")
    private int amount;

    /**
     * Initializes a newly created {@code ProductInBucket} object so that it represents
     * the product in bucket
     *
     * @param user    A {@code User}
     * @param product A {@code Product}
     * @param amount  A {@code int}
     */
    public ProductInBucket(User user, Product product, int amount) {
        this.user = user;
        this.product = product;
        this.amount = amount;
    }

}
