package by.it.academy.services.bucket;

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

    T getByUserAndProduct(User user, Product product);

    T getById(int id);

    boolean add(User user, Product product);

    List<T> getByUser(User user);

    List<ProductInBucket> getProductsInBucket(User user);

    boolean deleteAmountProducts(List<ProductInBucket> productsInBucket, Product product, int amount);

    double getAllCost(List<ProductInBucket> productsInBucket);

    boolean deleteAllProducts(List<ProductInBucket> productsInBucket);

    boolean buy(List<ProductInBucket> productsInBucket);
}
