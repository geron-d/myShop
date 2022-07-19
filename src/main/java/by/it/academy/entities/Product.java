package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name = "name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "date_inserting")
    private LocalDate dateInserting = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;

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
