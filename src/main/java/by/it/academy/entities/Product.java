package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The {@code Product} class represents the product.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    /**
     * An identifier of {@code Product}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A category of product.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * A type of product.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "type_id")
    private Type type;

    /**
     * A name of product.
     */
    @Column(name = "name")
    private String name;

    /**
     * A path where stored an image of product.
     */
    @Column(name = "image_path")
    private String imagePath;

    /**
     * A date where product inserted.
     */
    @Column(name = "date_inserting")
    private LocalDate dateInserting = LocalDate.now();

    /**
     * A producer of product.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    /**
     * Amount of product in the store.
     */
    @Column(name = "amount")
    private int amount;

    /**
     * Price of product.
     */
    @Column(name = "price")
    private double price;

    /**
     * Initializes a newly created {@code Product} object so that it represents
     * the product
     *
     * @param category      A {@code Category}
     * @param type          A {@code Type}
     * @param name          A {@code String}
     * @param imagePath     A {@code String}
     * @param dateInserting A {@code LocalDate}
     * @param producer      A {@code Producer}
     * @param amount        An {@code int}
     * @param price         A {@code double}
     */
    public Product(Category category, Type type, String name, String imagePath, LocalDate dateInserting,
                   Producer producer, int amount, double price) {
        this.category = category;
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
        this.dateInserting = dateInserting;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
    }

    /**
     * Initializes a newly created {@code Product} object so that it represents
     * the product
     *
     * @param category  A {@code Category}
     * @param type      A {@code Type}
     * @param name      A {@code String}
     * @param imagePath A {@code String}
     * @param producer  A {@code Producer}
     * @param amount    An {@code int}
     * @param price     A {@code double}
     */
    public Product(Category category, Type type, String name, String imagePath, Producer producer, int amount,
                   double price) {
        this.category = category;
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
    }

    /**
     * Initializes a newly created {@code Product} object so that it represents
     * the product
     *
     * @param product A {@code Product}
     */
    public Product(Product product) {
        this.category = product.getCategory();
        this.type = product.getType();
        this.name = product.getName();
        this.imagePath = product.getImagePath();
        this.dateInserting = product.getDateInserting();
        this.producer = product.getProducer();
        this.amount = product.getAmount();
        this.price = product.getPrice();
    }

}
