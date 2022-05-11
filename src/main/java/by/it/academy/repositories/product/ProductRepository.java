package by.it.academy.repositories.product;

import by.it.academy.contants.Order;

import java.util.List;

public interface ProductRepository<T> {
    boolean create(T t);

    T get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAllProducts(Order order);

    List<T> getLastProducts(int amount, Order order);

    List<T> getProductsInCategory(String category, Order order);

    T getByID(int id);

    List<T> search(String search);

    List<T> getProductsInType(String type, Order order);
}
