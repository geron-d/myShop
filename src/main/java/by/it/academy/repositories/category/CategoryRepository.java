package by.it.academy.repositories.category;

import by.it.academy.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByName(String name);

    List<Category> searchAllByNameContains(String search);

}
