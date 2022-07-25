package by.it.academy.services.category;

import by.it.academy.dtos.requests.category.CategoryDTO;
import by.it.academy.entities.Category;

import java.util.List;

/**
 * Interface for generic operations on a service for a category of product.
 *
 * @author Maxim Zhevnov
 */
public interface CategoryService {

    /**
     * Return a category by its id.
     *
     * @return the category with the given id or throw NoSUchElementException if category none found.
     */
    Category findCategory(Long id);

    /**
     * Save a category by given CategoryDTO.
     *
     * @return the id of saved category. Throw SQLIntegrityConstraintViolationException when category
     * with such name exists.
     */
    Long createCategory(CategoryDTO request);

    /**
     * Update a category by given id and CategoryDTO.
     *
     * @return the id of updated category. Throw NoSUchElementException if category none found.
     * Throw SQLIntegrityConstraintViolationException when category with such name exists.
     */
    Long updateCategory(Long id, CategoryDTO dto);

    /**
     * Delete a category by given id.
     *
     * Throw NoSUchElementException if category none found.
     */
    void deleteCategory(Long id);

    /**
     * Find category by its name.
     *
     * @return the category with the given name. Throw NoSUchElementException if category none found.
     */
    Category findCategory(String name);

    /**
     * Find all categories.
     *
     * @return all categories.
     */
    List<Category> findCategories();

    /**
     * Search categories by given search word in their names.
     *
     * @return categories by given search word.
     */
    List<Category> searchCategories(String search);

    /**
     * Find categories by given list of their names.
     *
     * @return categories by given list of their names.
     */
    List<Category> findCategories(List<String> categoryNames);

}
