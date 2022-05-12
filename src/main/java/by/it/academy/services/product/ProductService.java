package by.it.academy.services.product;

import by.it.academy.contants.Order;
import by.it.academy.entities.Product;

import java.util.List;

public interface ProductService<T> {
    boolean create(T t);

    Product get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAllProducts(Order order);

    T getByID(int id);

    List<T> getLastProducts(int amount, Order order);

    List<T> getProductsInCategory(String category, Order order);

    boolean checkProductAmount(T t);

    boolean decreaseProductAmount(T T, int amount);

    List<T> search(String search);

    List<T> getProductsInType(String type, Order order);

    List<T> sortByCategory(String[] categories);

    List<T> sortByType(String[] types);

    List<T> sort(String[] categories, String[] types);
}
