package by.it.academy.services.product;

import by.it.academy.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService<T> {
    boolean create(T t);

    Product get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> readAll();

    Product getByID(int id);
}
