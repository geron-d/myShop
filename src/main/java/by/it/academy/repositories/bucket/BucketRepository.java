package by.it.academy.repositories.bucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;

import java.util.List;

public interface BucketRepository<T> {
    boolean create(T t);

    T get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    T getByUserAndProduct(User user, Product product);

    List<T> getByUser(User user);

    T getById(int id);
}
