package by.it.academy.repositories.product;

import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for generic operations on a repository for a product.
 *
 * @author Maxim Zhevnov
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find products by their name.
     *
     * @return products by their name.
     */
    List<Product> findAllByName(String name);

    /**
     * Find products by their category.
     *
     * @return products by their category.
     */
    List<Product> findAllByCategory(Category category);

    /**
     * Find products by their type.
     *
     * @return products by their type.
     */
    List<Product> findAllByType(Type type);

    /**
     * Find products by their producer.
     *
     * @return products by their producer.
     */
    List<Product> findAllByProducer(Producer producer);

    /**
     * Search products by given search word in their names.
     *
     * @return products by given search word in their names.
     */
    List<Product> searchAllByNameContains(String search);

    /**
     * Check product by its category, producer, type, name.
     *
     * @return true if such product exists and false when does not.
     */
    boolean existsByCategoryAndProducerAndTypeAndName(Category category, Producer producer, Type type, String name);

}
