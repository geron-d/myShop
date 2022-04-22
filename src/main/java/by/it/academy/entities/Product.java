package by.it.academy.entities;

import java.time.LocalDate;
import java.util.Objects;

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


    public Product(int id, String category, String type, String name, String image_path, LocalDate localDate, String producer, int amount, double price) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.name = name;
        this.image_path = image_path;
        this.localDate = localDate;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
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

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && amount == product.amount && Double.compare(product.price, price) == 0 && Objects.equals(category, product.category) && Objects.equals(type, product.type) && Objects.equals(name, product.name) && Objects.equals(image_path, product.image_path) && Objects.equals(localDate, product.localDate) && Objects.equals(producer, product.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, type, name, image_path, localDate, producer, amount, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", image_path='" + image_path + '\'' +
                ", localDate=" + localDate +
                ", producer='" + producer + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
