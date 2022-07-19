package by.it.academy.services.product;

import by.it.academy.comparators.product.ProductIdDescComparator;
import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.dtos.requests.product.SortProductRequest;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.exceptions.product.HaveNotEnoughProductException;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.category.CategoryService;
import by.it.academy.services.producer.ProducerService;
import by.it.academy.services.type.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAPIService implements ProductService<Product> {

    private final ProductRepository productRepository;
    private final CategoryService<Category> categoryService;
    private final TypeService<Type> typeService;
    private final ProducerService<Producer> producerService;

    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Long createProduct(ProductDTO dto) {
        if (checkProduct(dto)) {
            throw new EntityExistsException();
        }
        Product product = buildProduct(dto);
        return productRepository.save(product).getId();
    }

    @Override
    @Transactional
    public Long updateProduct(Long id, ProductDTO dto) {
        if (!checkProduct(id)) {
            throw new NoSuchElementException();
        } else if (checkProduct(dto)) {
            throw new EntityExistsException();
        }
        Product product = buildProduct(dto);
        product.setId(id);
        return productRepository.save(product).getId();
    }

    @Override
    public Long updateProduct(Product product) {
        if (!checkProduct(product.getId())) {
            throw new NoSuchElementException();
        }
        return productRepository.save(product).getId();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProducts(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    @Transactional
    public List<Product> findProducts(int amountLastProducts) {
        Pageable lastProducts = PageRequest.of(0, amountLastProducts, Sort.by("id").descending());
        return productRepository.findAll(lastProducts).getContent();
    }

    @Override
    @Transactional
    public List<Product> findProductsByCategoryName(String category) {
        return productRepository.findAllByCategory(categoryService.findCategory(category));
    }

    @Override
    public List<Product> findProducts(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findProducts(Type type) {
        return productRepository.findAllByType(type);
    }

    @Override
    public List<Product> findProductsByTypeName(String type) {
        return productRepository.findAllByType(typeService.findType(type));
    }

    @Override
    public List<Product> findProducts(Producer producer) {
        return productRepository.findAllByProducer(producer);
    }

    @Override
    public List<Product> findProductsByProducerName(String producer) {
        return productRepository.findAllByProducer(producerService.findProducer(producer));
    }

    @Override
    @Transactional
    public List<Product> searchProducts(String search) {
        List<Product> products = productRepository.searchAllByNameContains(search);
        products.addAll(searchProductsByCategories(search));
        products.addAll(searchProductsByTypes(search));
        products.addAll(searchProductsByProducers(search));
        return products.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private boolean checkProductAmount(Product product, int amount) {
        return Optional.ofNullable(product).filter(value -> value.getAmount() >= amount).isPresent();
    }

    @Override
    @Transactional
    public Long decreaseProductAmount(ProductDecreaseRequest request) {
        Product product = findProduct(request.getProductId());
        if (!checkProductAmount(product, request.getAmount())) {
            throw new HaveNotEnoughProductException("haven't enough product");
        }
        product.setAmount(product.getAmount() - request.getAmount());
        return updateProduct(product);
    }

    @Override
    @Transactional
    public List<Product> sortProducts(SortProductRequest request) {
        List<Product> products = findProductsByCategories(request.getCategories());
        products.addAll(findProductsByTypes(request.getTypes()));
        products.addAll(findProductsByProducers(request.getProducers()));
        return products.stream()
                .distinct()
                .sorted(new ProductIdDescComparator())
                .collect(Collectors.toList());
    }

    private List<Product> findProductsByCategories(List<String> categories) {
        List<Product> products = new ArrayList<>();
        for (String category : categories) {
            products.addAll(findProductsByCategoryName(category));
        }
        return products;
    }

    private List<Product> findProductsByTypes(List<String> types) {
        List<Product> products = new ArrayList<>();
        for (String type : types) {
            products.addAll(findProductsByTypeName(type));
        }
        return products;
    }

    private List<Product> findProductsByProducers(List<String> producers) {
        List<Product> products = new ArrayList<>();
        for (String producer : producers) {
            products.addAll(findProductsByProducerName(producer));
        }
        return products;
    }

    private List<Product> searchProductsByCategories(List<Category> categories) {
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            products.addAll(findProducts(category));
        }
        return products;
    }

    private List<Product> searchProductsByCategories(String search) {
        return searchProductsByCategories(categoryService.searchCategories(search));
    }

    private List<Product> searchProductsByTypes(List<Type> types) {
        List<Product> products = new ArrayList<>();
        for (Type type : types) {
            products.addAll(findProducts(type));
        }
        return products;
    }

    private List<Product> searchProductsByTypes(String search) {
        return searchProductsByTypes(typeService.searchTypes(search));
    }

    private List<Product> searchProductsByProducers(List<Producer> producers) {
        List<Product> products = new ArrayList<>();
        for (Producer producer : producers) {
            products.addAll(findProducts(producer));
        }
        return products;
    }

    private List<Product> searchProductsByProducers(String search) {
        return searchProductsByProducers(producerService.searchProducers(search));
    }

    private boolean checkProduct(ProductDTO dto) {
        Category category = categoryService.findCategory(dto.getCategory());
        Type type = typeService.findType(dto.getType());
        Producer producer = producerService.findProducer(dto.getProducer());
        return productRepository.existsByCategoryAndProducerAndTypeAndName(category, producer, type, dto.getName());
    }

    private boolean checkProduct(Long id) {
        return productRepository.existsById(id);
    }

    private Product buildProduct(ProductDTO dto) {
        return Product.builder()
                .category(categoryService.findCategory(dto.getCategory()))
                .type(typeService.findType(dto.getType()))
                .name(dto.getName())
                .imagePath(dto.getImagePath())
                .dateInserting(LocalDate.now())
                .producer(producerService.findProducer(dto.getProducer()))
                .price(dto.getPrice())
                .amount(dto.getAmount())
                .build();
    }

}
