package by.it.academy.repositories.hiber.product;

import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import by.it.academy.repositories.hiber.type.TypeRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<T> {

    Optional<T> getProductById(int id);

    Optional<T> saveProduct(T t);

    void deleteProduct(T t);

    List<T> getAllProducts(Order order);

    Optional<T> getProductByCategoryTypeProducerName(T t);

    Optional<T> setProduct(T t);

    Optional<T> getProductByValuableFields(T t);

    List<T> getLastProducts(int amount, Order order);

    List<T> getProductsByCategory(Category category, Order order);

    List<T> search(String search);

    List<T> getProductsByType(Type type, Order order);

    CategoryRepository<Category> getCategoryRepository();

    TypeRepository<Type> getTypeRepository();

    ProducerRepository<Producer> getProducerRepository();
}
