package by.it.academy.services.bucket;

import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;

import java.util.List;

public interface BucketService<T> {
    boolean create(T t);

    T get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> getAll();

    public T getByUserAndProduct(User user, Product product);

    boolean add(User user, Product product);
}
