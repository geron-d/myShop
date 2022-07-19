package by.it.academy.services.product;

import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;

import java.util.List;
import java.util.Optional;

public interface ProductService<T> {

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

    boolean checkProductAmount(T t);

    Optional<Product> decreaseProductAmount(T t, int amount);

    List<T> sortByCategory(List<Category> categories);

    List<T> sortByType(List<Type> types);

    List<T> sort(String[] categories, String[] types);
}
