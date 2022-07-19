package by.it.academy.repositories.hiber.category;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for a specific type of category of product.
 *
 * @param <T> the type of the entity to handle
 * @author Maxim Zhevnov
 */
public interface CategoryRepository<T> {

    /**
     * Retrieves a category by its id.
     *
     * @param id must not be less than 1.
     * @return the category with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getCategoryById(int id);

    /**
     * Saves a given category. Use the returned instance for further operations as the save operation might have
     * changed the category instance completely.
     *
     * @param t must not be {@literal null}.
     * @return the saved category; will never be {@literal null}.
     */
    Optional<T> saveCategory(T t);

    /**
     * Deletes a given category.
     *
     * @param t must not be {@literal null}.
     */
    void deleteCategory(T t);

    /**
     * Retrieves a category by its name.
     *
     * @param categoryName must not be {@literal null}.
     * @return the category with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getCategoryByName(String categoryName);

    /**
     * Returns all instances of the category in straight or reverse order.
     *
     * @param order must be ASC or DESC.
     * @return all categories
     */
    List<T> getAllCategories(Order order);

    /**
     * Retrieves a category by its valuable fields.
     *
     * @param t must not be {@literal null}.
     * @return the category with the given valuable fields or {@literal Optional#empty()} if none found.
     */
    Optional<T> getCategoryByValuableFields(T t);
}
