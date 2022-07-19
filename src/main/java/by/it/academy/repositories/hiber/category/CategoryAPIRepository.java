package by.it.academy.repositories.hiber.category;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryAPIRepository implements CategoryRepository<Category> {
    Logger log = Logger.getLogger(CategoryAPIRepository.class);
    private final Session session;

    public CategoryAPIRepository(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        Optional<Category> category = Optional.empty();
        try {
            category = Optional.of(session.get(Category.class, id));
            log.info("CategoryAPIRepository - method: getCategoryById: " + category);
        } catch (NullPointerException e) {
            log.info("CategoryAPIRepository - method: getCategoryById: " + e);
            return category;
        }
        return category;
    }

    @Override
    public Optional<Category> saveCategory(Category category) {
        Optional<Category> optionalCategory = Optional.ofNullable(category);

        if (optionalCategory.isPresent()) {
            if ((Objects.isNull(optionalCategory.get().getId())
                    || !getCategoryById(optionalCategory.get().getId()).isPresent())
                    && !getCategoryByValuableFields(optionalCategory.get()).isPresent()) {
                session.persist(optionalCategory.get());
                log.info("CategoryAPIRepository - method: saveCategory: persist: " + optionalCategory);
                return getCategoryByValuableFields(optionalCategory.get());
            } else if (!getCategoryByValuableFields(optionalCategory.get()).isPresent()) {
                session.merge(optionalCategory.get());
                log.info("CategoryAPIRepository - method: saveCategory: merge: " + optionalCategory);
                return getCategoryByValuableFields(optionalCategory.get());
            } else {
                log.info("CategoryAPIRepository - method: saveCategory: category already exist");
            }
        }

        return optionalCategory;
    }

    @Override
    public void deleteCategory(Category category) {
        Optional<Category> optionalCategory = Optional.ofNullable(category);

        if (optionalCategory.isPresent() && Objects.nonNull(optionalCategory.get().getId())) {
            session.delete(optionalCategory.get());
            log.info("CategoryAPIRepository - method: deleteCategory: remove: " + optionalCategory);
        } else {
            log.info("CategoryAPIRepository - method: deleteCategory: category doesn't exist");
        }
    }

    @Override
    public Optional<Category> getCategoryByName(String categoryName) {
        Optional<Category> category = Optional.empty();
        try {
            TypedQuery<Category> query = session.createQuery(HQL.GET_CATEGORY_BY_NAME_HQL, Category.class);
            query.setParameter("category", categoryName);
            category = Optional.of(query.getSingleResult());
            log.info("CategoryAPIRepository - method: getCategoryByCategory: " + category);
        } catch (NoResultException e) {
            log.info("CategoryAPIRepository - method: getCategoryByCategory: " + e);
            return category;
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories(Order order) {
        List<Category> categories;

        TypedQuery<Category> query = session.createQuery(HQL.GET_ALL_CATEGORIES_HQL, Category.class);
        TypedQuery<Category> queryDesc = session.createQuery(HQL.GET_ALL_CATEGORIES_DESC_HQL, Category.class);
        categories = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("CategoryAPIRepository - method: getAllCategories: " + categories);

        return categories;
    }

    @Override
    public Optional<Category> getCategoryByValuableFields(Category category) {
        Optional<Category> optionalCategory = Optional.ofNullable(category);
        if (optionalCategory.isPresent()) {
            return getCategoryByName(optionalCategory.get().getCategory());
        } else {
            return optionalCategory;
        }
    }

    public Session getSession() {
        return session;
    }

}
