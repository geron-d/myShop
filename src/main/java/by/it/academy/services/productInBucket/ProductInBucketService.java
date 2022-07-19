package by.it.academy.services.productInBucket;

import by.it.academy.dtos.requests.productInBucket.ProductInBucketDTO;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;

import java.util.List;

public interface ProductInBucketService<T> {

    T findProductInBucket(Long id);

    Long createProductInBucket(ProductInBucketDTO dto);

    Long createProductInBucket(T productInBucket);

    Long updateProductInBucket(T productInBucket);

    Long updateProductInBucket(Long id, ProductInBucketDTO dto);

    void deleteProductInBucket(Long id);

    Long addProductInBucket(ProductInBucketDTO dto);

    Long addProductInBucket(T productInBucket);

    T findProductInBucket(User user, Product product);

    boolean checkAmountProductInBucket(T productInBucket, int amount);

    void deleteProductsInBucket(User user);

    List<T> findProductsInBucket(User user);

    void deleteProductInBucket(T t);

}
