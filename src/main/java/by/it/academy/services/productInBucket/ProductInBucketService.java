package by.it.academy.services.productInBucket;

import by.it.academy.dtos.requests.productInBucket.ProductInBucketDTO;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;

import java.util.List;

/**
 * Interface for generic operations on a service for a productInBucket.
 *
 * @author Maxim Zhevnov
 */
public interface ProductInBucketService {

    /**
     * Return a productInBucket by its id.
     *
     * @return the productInBucket with the given id or throw NoSUchElementException if productInBucket not found.
     */
    ProductInBucket findProductInBucket(Long id);

    /**
     * Save a productInBucket by given ProductInBucket.
     *
     * @return the id of saved productInBucket. Throw EntityExistsException when productInBucket
     * with such user and product exists.
     */
    Long createProductInBucket(ProductInBucket productInBucket);

    /**
     * Update a productInBucket by given id and ProductInBucket.
     *
     * @return the id of updated productInBucket. Throw NoSUchElementException if productInBucket not found.
     * Throw EntityExistsException when productInBucket with such user and product exists.
     */
    Long updateProductInBucket(ProductInBucket productInBucket);

    /**
     * Delete a productInBucket by given id.
     *
     * Throw NoSUchElementException if productInBucket none found.
     */
    void deleteProductInBucket(Long id);

    /**
     * Add a productInBucket to user bucket by given ProductInBucketDTO.
     *
     * @return the id of added productInBucket. Throw NoSUchElementException if user or product not found.
     */
    Long addProductInBucket(ProductInBucketDTO dto);

    /**
     * Find a productInBucket by given User and Product.
     *
     * @return the productInBucket by given User and Product. Throw NoSUchElementException if user or product not found.
     */
    ProductInBucket findProductInBucket(User user, Product product);

    /**
     * Check amount productInBucket by given ProductInBucket and amount.
     *
     * @return true if amount productInBucket more or equals given amount.
     * Throw NoSUchElementException if productInBucket not found.
     */
    boolean checkAmountProductInBucket(ProductInBucket productInBucket, int amount);

    /**
     * Delete all productsInBucket by given user.
     *
     * Throw NoSUchElementException if user none found.
     */
    void deleteProductsInBucket(User user);

    /**
     * Find productsInBucket by given user.
     *
     * @return productsInBucket by given user. Throw NoSUchElementException if user none found.
     */
    List<ProductInBucket> findProductsInBucket(User user);

    /**
     * Delete a productInBucket by given ProductInBucket.
     *
     * Throw NoSUchElementException if productInBucket none found.
     */
    void deleteProductInBucket(ProductInBucket productInBucket);

}
