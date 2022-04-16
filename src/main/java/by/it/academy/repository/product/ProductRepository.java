package by.it.academy.repository.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<T> {
    boolean create(T t);

    Optional<T> get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> readAll();
}
