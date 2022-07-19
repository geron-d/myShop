package by.it.academy.services.category;

import by.it.academy.dtos.requests.category.CategoryDTO;

import java.util.List;

public interface CategoryService<T> {

    T findCategory(Long id);

    Long createCategory(CategoryDTO request);

    Long updateCategory(Long id, CategoryDTO dto);

    void deleteCategory(Long id);

    T findCategory(String name);

    List<T> findCategories();

    List<T> searchCategories(String search);

    List<T> findCategories(List<String> categoryNames);

}
