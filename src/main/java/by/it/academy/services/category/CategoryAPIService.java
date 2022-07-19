package by.it.academy.services.category;

import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CategoryAPIService implements CategoryService<Category> {
    private final Session session;
    private final CategoryRepository<Category> categoryRepository;

    public CategoryAPIService(Session session, CategoryRepository<Category> categoryRepository) {
        this.session = session;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> category = categoryRepository.getCategoryById(id);

        transaction.commit();

        return category;
    }

    @Override
    public Optional<Category> saveCategory(Category category) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> optionalCategory = categoryRepository.saveCategory(category);

        transaction.commit();

        return optionalCategory;
    }

    @Override
    public void deleteCategory(Category category) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        categoryRepository.deleteCategory(category);

        transaction.commit();
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(categoryName);

        transaction.commit();

        return optionalCategory;
    }

    @Override
    public List<Category> getAllCategories(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Category> categories = categoryRepository.getAllCategories(order);

        transaction.commit();

        return categories;
    }

    @Override
    public Optional<Category> getCategoryByValuableFields(Category category) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> optionalCategory = categoryRepository.getCategoryByValuableFields(category);

        transaction.commit();

        return optionalCategory;
    }
}
