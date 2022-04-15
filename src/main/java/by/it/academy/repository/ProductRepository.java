package by.it.academy.repository;

import java.util.List;

public interface ProductRepository<T> {
    boolean create(T t);

    T get(T t);

    List<T> readAll();
}
