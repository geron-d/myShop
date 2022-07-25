package by.it.academy.services.product;

import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.entities.Product;

import java.util.List;

/**
 * Interface for generic operations on a service for a product.
 *
 * @author Maxim Zhevnov
 */
public interface ProductService {

    /**
     * Return a product by its id.
     *
     * @return the product with the given id or throw NoSUchElementException if product not found.
     */
    Product findProduct(Long id);

    /**
     * Save a product by given ProductDTO.
     *
     * @return the id of saved product. Throw SQLIntegrityConstraintViolationException when product
     * with such name, category, type and producer exists.
     */
    Long createProduct(ProductDTO request);

    /**
     * Update a product by given id and ProductDTO.
     *
     * @return the id of updated product. Throw NoSUchElementException if product not found.
     * Throw SQLIntegrityConstraintViolationException when product with such name, category, type and producer exists.
     */
    Long updateProduct(Long id, ProductDTO dto);

    /**
     * Update a product by given Product.
     *
     * @return the id of updated product. Throw NoSUchElementException if product not found.
     * Throw SQLIntegrityConstraintViolationException with such name, category, type and producer exists.
     */
    Long updateProduct(Product product);

    /**
     * Delete a product by given id.
     *
     * Throw NoSUchElementException if product none found.
     */
    void deleteProduct(Long id);

    /**
     * Find all products.
     *
     * @return all products.
     */
    List<Product> findProducts();

    /**
     * Find products by their name.
     *
     * @return products by their name.
     */
    List<Product> findProducts(String name);

    /**
     * Find last products by given amount.
     *
     * @return last products by given amount.
     */
    List<Product> findProducts(int amount);

    /**
     * Find products by given category name.
     *
     * @return products by given category name.
     */
    List<Product> findProductsByCategoryName(String category);

    /**
     * Find products by given type name.
     *
     * @return products by given type name.
     */
    List<Product> findProductsByTypeName(String type);

    /**
     * Find products by given producer name.
     *
     * @return products by given producer name.
     */
    List<Product> findProductsByProducerName(String producer);

    /**
     * Find products by given search word. Find in the name of category, name of type,
     * name of producer, name of product.
     *
     * @return products by given search word.
     */
    List<Product> searchProducts(String search);

    /**
     * Decrease product amount on the store by given ProductDecreaseRequest.
     *
     * @return id decreased product.
     * Throw NoSUchElementException if product none found. Throw NotEnoughProductException if product amount
     * not enough on the store.
     */
    Long decreaseProductAmount(ProductDecreaseRequest request);

}
