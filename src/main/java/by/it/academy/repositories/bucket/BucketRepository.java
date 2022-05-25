package by.it.academy.repositories.bucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface BucketRepository<T> {
    boolean create(T t);

    Optional<T> get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<Optional<T>> getAllBucket();

    Optional<T> getByUserAndProduct(User user, Product product);

    List<Optional<T>> getByUser(User user);

    Optional<T> getById(int id);
}
