package by.it.academy.services.category;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface CategoryService<T> {

    Optional<T> getCategoryById(int id);

    Optional<T> saveCategory(T t);

    void deleteCategory(T t);

    Optional<T> getCategoryByName(String categoryName);

    List<T> getAllCategories(Order order);

    Optional<T> getCategoryByValuableFields(T t);
}
