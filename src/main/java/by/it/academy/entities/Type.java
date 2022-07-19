package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The {@code Type} class represents the type of product.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {

    /**
     * An identifier of {@code Type}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A name of the type of product.
     */
    @Column(name = "name", unique = true, length = 30)
    private String name;

    /**
     * Initializes a newly created {@code Type} object so that it represents
     * the name of type
     *
     * @param type A {@code String}
     */
    public Type(String type) {
        this.name = type;
    }

}
