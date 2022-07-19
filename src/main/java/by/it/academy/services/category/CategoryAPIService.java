package by.it.academy.services.category;

import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.services.CategoryService interface.
 *
 * @author Maxim Zhevnov
 */
public class CategoryAPIService implements CategoryService<Category> {
    private final Session session;
    private final CategoryRepository<Category> categoryRepository;

    /**
     * Creates a new {@link CategoryAPIService} to manage objects of the given {@link Session}
     * and {@link CategoryRepository}.
     *
     * @param session must not be {@literal null}.
     * @param categoryRepository must not be {@literal null}.
     */
    public CategoryAPIService(Session session, CategoryRepository<Category> categoryRepository) {
        this.session = session;
        this.categoryRepository = categoryRepository;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#getCategoryById
     */
    @Override
    public Optional<Category> getCategoryById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> category = categoryRepository.getCategoryById(id);

        transaction.commit();

        return category;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#saveCategory
     */
    @Override
    public Optional<Category> saveCategory(Category category) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> optionalCategory = categoryRepository.saveCategory(category);

        transaction.commit();

        return optionalCategory;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#deleteCategory
     */
    @Override
    public void deleteCategory(Category category) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        categoryRepository.deleteCategory(category);

        transaction.commit();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#getCategoryByName
     */
    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(categoryName);

        transaction.commit();

        return optionalCategory;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#getAllCategories
     */
    @Override
    public List<Category> getAllCategories(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Category> categories = categoryRepository.getAllCategories(order);

        transaction.commit();

        return categories;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#getCategoryByValuableFields
     */
    @Override
    public Optional<Category> getCategoryByValuableFields(Category category) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Category> optionalCategory = categoryRepository.getCategoryByValuableFields(category);

        transaction.commit();

        return optionalCategory;
    }
}
