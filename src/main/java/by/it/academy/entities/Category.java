package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The {@code Category} class represents the category of product.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    /**
     * An identifier of {@code Category}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A name of the category of product.
     */
    @Column(name = "name", unique = true, length = 30)
    private String name;

    /**
     * Initializes a newly created {@code Category} object so that it represents
     * the name of category
     *
     * @param category A {@code String}
     */
    public Category(String category) {
        this.name = category;
    }

}