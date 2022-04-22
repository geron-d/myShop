package by.it.academy.repositories.product;

import by.it.academy.entities.Product;

import java.util.List;

public interface ProductRepository<T> {
    boolean create(T t);

    Product get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();
}
