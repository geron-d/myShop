package by.it.academy.repositories.product;

import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByName(String name);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByType(Type type);

    List<Product> findAllByProducer(Producer producer);

    List<Product> searchAllByNameContains(String search);

    boolean existsByCategoryAndProducerAndTypeAndName(Category category, Producer producer, Type type, String name);

}
