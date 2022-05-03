package by.it.academy.services.product;

import by.it.academy.entities.Product;

import java.util.List;

public interface ProductService<T> {
    boolean create(T t);

    Product get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    T getByID(int id);

    List<T> getLastProducts(int amount);

    List<T> getCategoryDesc(String category);

    boolean checkProductAmount(T t);

    List<T> getAllDesc();

    boolean decreaseProductAmount(T T, int amount);

    List<T> search(String search);
}
