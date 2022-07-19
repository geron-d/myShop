package by.it.academy.repositories.productInBucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInBucketRepository extends JpaRepository<ProductInBucket, Long> {

    ProductInBucket findByUserAndProduct(User user, Product product);

    boolean existsByUserAndProduct(User user, Product product);

    List<ProductInBucket> findAllByUser(User user);

}
