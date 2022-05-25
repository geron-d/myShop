package by.it.academy.repositories.product;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<T> {
    boolean create(T t);

    Optional<T> get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<Optional<T>> getAllProducts(Order order);

    List<Optional<T>> getLastProducts(int amount, Order order);

    List<Optional<T>> getProductsInCategory(String category, Order order);

    Optional<T> getByID(int id);

    List<Optional<T>> search(String search);

    List<Optional<T>> getProductsInType(String type, Order order);
}
