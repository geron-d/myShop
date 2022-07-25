package by.it.academy.repositories.productInBucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for a product in bucket.
 *
 * @author Maxim Zhevnov
 */
public interface ProductInBucketRepository extends JpaRepository<ProductInBucket, Long> {

    /**
     * Return a productInBucket by user and product.
     *
     * @return the productInBucket by user and product or throw NoSUchElementException if user or product not found.
     */
    Optional<ProductInBucket> findByUserAndProduct(User user, Product product);

    /**
     * Check productInBucket by user and product.
     *
     * @return true if such productInBucket exists and false when does not.
     */
    boolean existsByUserAndProduct(User user, Product product);

    /**
     * Find productsInBucket by their user.
     *
     * @return productsInBucket by their user.
     */
    List<ProductInBucket> findAllByUser(User user);

}
