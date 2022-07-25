package by.it.academy.services.product;

import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.exceptions.product.NotEnoughProductException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the by.it.academy.services.ProductService interface.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAPIService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final TypeService typeService;
    private final ProducerService producerService;

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#findProduct(Long id)
     */
    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#createProduct(ProductDTO dto)
     */
    @Override
    @Transactional
    public Long createProduct(ProductDTO dto) {
        if (checkProduct(dto)) {
            throw new EntityExistsException();
        }
        Product product = buildProduct(dto);
        return productRepository.save(product).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#updateProduct(Long id, ProductDTO dto)
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#updateProduct(Product product)
     */
    @Override
    public Long updateProduct(Product product) {
        if (!checkProduct(product.getId())) {
            throw new NoSuchElementException();
        } else if (checkProduct(product)) {
            throw new EntityExistsException();
        }
        return productRepository.save(product).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#deleteProduct(Long id)
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#findProducts()
     */
    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#findProducts(String name)
     */
    @Override
    public List<Product> findProducts(String name) {
        return productRepository.findAllByName(name);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#findProducts(int amountLastProducts)
     */
    @Override
    @Transactional
    public List<Product> findProducts(int amountLastProducts) {
        Pageable lastProducts = PageRequest.of(0, amountLastProducts, Sort.by("id").descending());
        return productRepository.findAll(lastProducts).getContent();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#findProductsByCategoryName(String category)
     */
    @Override
    @Transactional
    public List<Product> findProductsByCategoryName(String category) {
        return productRepository.findAllByCategory(categoryService.findCategory(category));
    }

    /**
     * Find products by given category.
     *
     * @return products by given category.
     */
    private List<Product> findProducts(Category category) {
        return productRepository.findAllByCategory(category);
    }

    /**
     * Find products by given type.
     *
     * @return products by given type.
     */
    private List<Product> findProducts(Type type) {
        return productRepository.findAllByType(type);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#findProductsByTypeName(String type)
     */
    @Override
    public List<Product> findProductsByTypeName(String type) {
        return productRepository.findAllByType(typeService.findType(type));
    }

    /**
     * Find products by given producer.
     *
     * @return products by given producer.
     */
    private List<Product> findProducts(Producer producer) {
        return productRepository.findAllByProducer(producer);
    }

    @Override
    public List<Product> findProductsByProducerName(String producer) {
        return productRepository.findAllByProducer(producerService.findProducer(producer));
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#searchProducts(String search)
     */
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

    /**
     * Check product amount by given product and amount.
     *
     * @return true if product amount more or equals given amount and false when does not.
     */
    private boolean checkProductAmount(Product product, int amount) {
        return Optional.ofNullable(product).filter(value -> value.getAmount() >= amount).isPresent();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#decreaseProductAmount(ProductDecreaseRequest request)
     */
    @Override
    @Transactional
    public Long decreaseProductAmount(ProductDecreaseRequest request) {
        Product product = findProduct(request.getProductId());
        if (!checkProductAmount(product, request.getAmount())) {
            throw new NotEnoughProductException("haven't enough product");
        }
        product.setAmount(product.getAmount() - request.getAmount());
        return updateProduct(product);
    }

    /**
     * Search products by given list of categories.
     *
     * @return products by given list of categories.
     */
    private List<Product> searchProductsByCategories(List<Category> categories) {
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            products.addAll(findProducts(category));
        }
        return products;
    }

    /**
     * Search products which category names contains given search word.
     *
     * @return products which category names contains given search word.
     */
    private List<Product> searchProductsByCategories(String search) {
        return searchProductsByCategories(categoryService.searchCategories(search));
    }

    /**
     * Search products by given list of types.
     *
     * @return products by given list of types.
     */
    private List<Product> searchProductsByTypes(List<Type> types) {
        List<Product> products = new ArrayList<>();
        for (Type type : types) {
            products.addAll(findProducts(type));
        }
        return products;
    }

    /**
     * Search products which type names contains given search word.
     *
     * @return products which type names contains given search word.
     */
    private List<Product> searchProductsByTypes(String search) {
        return searchProductsByTypes(typeService.searchTypes(search));
    }

    /**
     * Search products by given list of producers.
     *
     * @return products by given list of producers.
     */
    private List<Product> searchProductsByProducers(List<Producer> producers) {
        List<Product> products = new ArrayList<>();
        for (Producer producer : producers) {
            products.addAll(findProducts(producer));
        }
        return products;
    }

    /**
     * Search products which producer names contains given search word.
     *
     * @return products which producer names contains given search word.
     */
    private List<Product> searchProductsByProducers(String search) {
        return searchProductsByProducers(producerService.searchProducers(search));
    }

    /**
     * Check product by its category, producer, type, name.
     *
     * @return true if such product exists and false when does not.
     */
    private boolean checkProduct(ProductDTO dto) {
        Category category = categoryService.findCategory(dto.getCategory());
        Type type = typeService.findType(dto.getType());
        Producer producer = producerService.findProducer(dto.getProducer());
        return productRepository.existsByCategoryAndProducerAndTypeAndName(category, producer, type, dto.getName());
    }

    /**
     * Check product by given product.
     *
     * @return true if such product exists and false when does not.
     */
    private boolean checkProduct(Product product) {
        return productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(), product.getProducer(),
                product.getType(), product.getName());
    }

    /**
     * Check product by given id.
     *
     * @return true if such product exists and false when does not.
     */
    private boolean checkProduct(Long id) {
        return productRepository.existsById(id);
    }

    /**
     * Build product by ProductDTO.
     *
     * @return product by ProductDTO.
     */
    private Product buildProduct(ProductDTO dto) {
        return Product.builder()
                .category(categoryService.findCategory(dto.getCategory()))
                .type(typeService.findType(dto.getType()))
                .name(dto.getName())
                .imagePath(dto.getImagePath())
                .dateInserting(dto.getDateInserting())
                .producer(producerService.findProducer(dto.getProducer()))
                .price(dto.getPrice())
                .amount(dto.getAmount())
                .build();
    }

}
