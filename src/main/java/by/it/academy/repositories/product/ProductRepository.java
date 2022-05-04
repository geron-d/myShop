package by.it.academy.repositories.product;

import java.util.List;

public interface ProductRepository<T> {
    boolean create(T t);

    T get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    List<T> getLastProducts(int amount);

    List<T> getCategoryDesc(String category);

    T getByID(int id);

    List<T> getAllDesc();

    List<T> search(String search);

    List<T> getTypeDesc(String type);
}
