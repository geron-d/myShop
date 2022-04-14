package by.it.academy.entities;

import java.util.Objects;

public class Product {
    private int id;
    private String category;
    private String type;
    private String name;
    private String image_path;

    public Product(int id, String category, String type, String name, String image_path) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.name = name;
        this.image_path = image_path;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(category, product.category) && Objects.equals(type, product.type) && Objects.equals(name, product.name) && Objects.equals(image_path, product.image_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, type, name, image_path);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", image_path='" + image_path + '\'' +
                '}';
    }
}
