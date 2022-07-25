package by.it.academy.repositories.category;

import by.it.academy.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for category of product.
 *
 * @author Maxim Zhevnov
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Return a category by its name.
     *
     * @return the category with the given name or throw NoSUchElementException if category not found.
     */
    Optional<Category> findCategoryByName(String name);

    /**
     * Search categories by given search word in their names.
     *
     * @return categories by given search word.
     */
    List<Category> searchAllByNameContains(String search);

}
