package by.it.academy.services.productInBucket;

import by.it.academy.contants.Order;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a service for a specific type of product in bucket.
 *
 * @param <T> the type of the entity to handle
 * @author Maxim Zhevnov
 */
public interface ProductInBucketService<T> {

    /**
     * Retrieves a product in bucket by its id.
     *
     * @param id must not be less than 1.
     * @return the product in bucket with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductInBucketById(int id);

    /**
     * Saves a given product in bucket. Use the returned instance for further operations as the save operation might
     * have changed the product in bucket instance completely.
     *
     * @param t must not be {@literal null}.
     * @return the saved product in bucket; will never be {@literal null}.
     */
    Optional<T> saveProductInBucket(T t);

    /**
     * Deletes a given product in bucket.
     *
     * @param t must not be {@literal null}.
     */
    void deleteProductInBucket(T t);

    /**
     * Returns all instances of the product in bucket in straight or reverse order.
     *
     * @param order must be ASC or DESC.
     * @return all products in bucket
     */
    List<T> getAllProductsInBucket(Order order);

    /**
     * Retrieves a product in bucket by its user and product.
     *
     * @param user    must not be {@literal null}.
     * @param product must not be {@literal null}.
     * @return the product in bucket with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductInBucketByUserProduct(User user, Product product);

    /**
     * Retrieves a product in bucket by its valuable fields.
     *
     * @param user    must not be {@literal null}.
     * @param product must not be {@literal null}.
     * @return the product in bucket with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductInBucketByValuableFields(User user, Product product);

    /**
     * Retrieves a product in bucket by its valuable fields.
     *
     * @param productInBucket must not be {@literal null}.
     * @return the product in bucket with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductInBucketByValuableFields(ProductInBucket productInBucket);

    /**
     * Returns all instances of the product in bucket by user.
     *
     * @param user must not be {@literal null}.
     * @return all products in bucket by user.
     */
    List<T> getProductInBucketByUser(User user);

    /**
     * Retrieves a product in bucket by its valuable fields.
     *
     * @param t must not be {@literal null}.
     * @param n must not be less than 1.
     * @return the product in bucket with added given amount.
     */
    Optional<T> addAmountToExistProductInBucket(T t, int n);

    /**
     * Add product to user's bucket by user and bucket.
     *
     * @param user    must not be {@literal null}.
     * @param product must not be {@literal null}.
     * @return the added product in bucket with.
     */
    Optional<T> add(User user, Product product);

    /**
     * Delete given amount given products from user's bucket.
     *
     * @param productsInBucket must not be {@literal null}.
     * @param product          must not be {@literal null}.
     * @param amount           must not be less than 1.
     */
    void deleteAmountProducts(List<T> productsInBucket, Product product, int amount);

    /**
     * Get full cost from all products in given users bucket.
     *
     * @param productsInBucket must not be {@literal null}.
     * @return full cost from all products in given users bucket.
     */
    double getAllCost(List<T> productsInBucket);

    /**
     * Delete all products from user's bucket.
     *
     * @param productsInBucket must not be {@literal null}.
     */
    void deleteAllProducts(List<T> productsInBucket);

    /**
     * Decreased product's amount on bought product's amount and cleared user's bucket
     *
     * @param productsInBucket must not be {@literal null}.
     */
    void buy(List<T> productsInBucket);
}
