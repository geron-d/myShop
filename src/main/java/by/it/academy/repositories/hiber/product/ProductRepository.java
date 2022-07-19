package by.it.academy.repositories.hiber.product;

import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import by.it.academy.repositories.hiber.type.TypeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for a specific type of product.
 *
 * @param <T> the type of the entity to handle
 * @author Maxim Zhevnov
 */
public interface ProductRepository<T> {

    /**
     * Retrieves a product by its id.
     *
     * @param id must not be less than 1.
     * @return the product with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductById(int id);

    /**
     * Saves a given product. Use the returned instance for further operations as the save operation might have
     * changed the product instance completely.
     *
     * @param t must not be {@literal null}.
     * @return the saved product; will never be {@literal null}.
     */
    Optional<T> saveProduct(T t);

    /**
     * Deletes a given product.
     *
     * @param t must not be {@literal null}.
     */
    void deleteProduct(T t);

    /**
     * Returns all instances of the product in straight or reverse order.
     *
     * @param order must be ASC or DESC.
     * @return all products
     */
    List<T> getAllProducts(Order order);

    /**
     * Retrieves a product by its category, type, producer, name.
     *
     * @param t must not be {@literal null}.
     * @return the product with the given category, type, producer, name or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductByCategoryTypeProducerName(T t);

    /**
     * Set product category, type, producer from database by its category, type, producer
     * or save them if they don't found in database/
     *
     * @param t must not be {@literal null}.
     * @return the product with the given category, type, producer or {@literal Optional#empty()} if none found.
     */
    Optional<T> setProduct(T t);

    /**
     * Retrieves a product by its valuable fields.
     *
     * @param t must not be {@literal null}.
     * @return the product with the given valuable fields or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProductByValuableFields(T t);

    /**
     * Returns amount instances of the product in straight or reverse order.
     *
     * @param amount must not be less than 1.
     * @param order  must be ASC or DESC.
     * @return all products
     */
    List<T> getLastProducts(int amount, Order order);

    /**
     * Returns instances of the product in straight or reverse order by their category.
     *
     * @param category must not be {@literal null}.
     * @param order    must be ASC or DESC.
     * @return all products
     */
    List<T> getProductsByCategory(Category category, Order order);

    /**
     * Returns instances of the product by given pattern.
     *
     * @return all products
     */
    List<T> search(String search);

    /**
     * Returns instances of the product in straight or reverse order by their type.
     *
     * @param type  must not be {@literal null}.
     * @param order must be ASC or DESC.
     * @return all products
     */
    List<T> getProductsByType(Type type, Order order);

    /**
     * Returns {@link CategoryRepository} which initialized {@link ProductRepository}.
     *
     * @return {@link CategoryRepository}
     */
    CategoryRepository<Category> getCategoryRepository();

    /**
     * Returns {@link TypeRepository} which initialized {@link ProductRepository}.
     *
     * @return {@link TypeRepository}
     */
    TypeRepository<Type> getTypeRepository();

    /**
     * Returns {@link ProducerRepository} which initialized {@link ProductRepository}.
     *
     * @return {@link ProducerRepository}
     */
    ProducerRepository<Producer> getProducerRepository();
}
