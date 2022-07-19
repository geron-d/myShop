package by.it.academy.repositories.hiber.productInBucket;

import by.it.academy.contants.Order;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface ProductInBucketRepository<T> {

    Optional<T> getProductInBucketById(int id);

    Optional<T> saveProductInBucket(T t);

    void deleteProductInBucket(T t);

    List<T> getAllProductsInBucket(Order order);

    Optional<T> getProductInBucketByUserProduct(User user, Product product);

    Optional<T> getProductInBucketByValuableFields(User user, Product product);

    Optional<T> getProductInBucketByValuableFields(ProductInBucket productInBucket);

    List<T> getProductInBucketByUser(User user);
}
