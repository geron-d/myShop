package by.it.academy.repositories.category;

import by.it.academy.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);

    List<Category> searchAllByNameContains(String search);

}
