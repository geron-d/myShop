package by.it.academy.services.product;

import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.dtos.requests.product.SortProductRequest;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Type;

import java.util.List;

public interface ProductService<T> {

    T findProduct(Long id);

    Long createProduct(ProductDTO request);

    Long updateProduct(Long id, ProductDTO dto);

    Long updateProduct(T product);

    void deleteProduct(Long id);

    List<T> findProducts();

    List<T> findProducts(String name);

    List<T> findProducts(int amount);

    List<T> findProductsByCategoryName(String category);

    List<T> findProducts(Category category);

    List<T> findProducts(Type type);

    List<T> findProductsByTypeName(String type);

    List<T> findProducts(Producer producer);

    List<T> findProductsByProducerName(String producer);

    List<T> searchProducts(String search);

    Long decreaseProductAmount(ProductDecreaseRequest request);

    List<T> sortProducts(SortProductRequest request);

}
