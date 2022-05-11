package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int id;
    private String category;
    private String type;
    private String name;
    private String image_path;
    private LocalDate localDate = LocalDate.now();
    private String producer;
    private int amount;
    private double price;

    public Product(Product product) {
        this.id = product.getId();
        this.category = product.getCategory();
        this.type = product.getType();
        this.name = product.getName();
        this.image_path = product.getImage_path();
        this.localDate = product.getLocalDate();
        this.producer = product.getProducer();
        this.amount = product.getAmount();
        this.price = product.getPrice();
    }

    public Product(String category, String type, String name, String image_path, LocalDate localDate, String producer, int amount, double price) {
        this.category = category;
        this.type = type;
        this.name = name;
        this.image_path = image_path;
        this.localDate = localDate;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
    }
}
