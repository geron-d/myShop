package by.it.academy.services.product;

import by.it.academy.entities.Product;

import java.util.List;

public interface ProductService<T> {
    boolean create(T t);

    Product get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    Product getByID(int id);

    List<Product> getLastFour();

    Product buyProduct(Product product);

    boolean isProductHas(Product product);
}
