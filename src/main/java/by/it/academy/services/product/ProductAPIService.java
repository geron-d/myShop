package by.it.academy.services.product;

import by.it.academy.comparators.ProductIdDescComparator;
import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.product.ProductAPIRepository;
import by.it.academy.repositories.hiber.product.ProductRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the by.it.academy.services.ProductService interface.
 *
 * @author Maxim Zhevnov
 */
public class ProductAPIService implements ProductService<Product> {
    private final Session session;
    private final ProductRepository<Product> productRepository;

    /**
     * Creates a new {@link ProductAPIRepository} to manage objects of the given session.
     *
     * @param session must not be {@literal null}.
     * @param productRepository must not be {@literal null}.
     */
    public ProductAPIService(Session session, ProductRepository<Product> productRepository) {
        this.session = session;
        this.productRepository = productRepository;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getProductById
     */
    @Override
    public Optional<Product> getProductById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Product> product = productRepository.getProductById(id);

        transaction.commit();

        return product;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#saveProduct
     */
    @Override
    public Optional<Product> saveProduct(Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Product> optionalProduct = productRepository.saveProduct(product);

        transaction.commit();

        return optionalProduct;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#deleteProduct
     */
    @Override
    public void deleteProduct(Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        productRepository.deleteProduct(product);

        transaction.commit();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getAllProducts
     */
    @Override
    public List<Product> getAllProducts(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Product> products = productRepository.getAllProducts(order);

        transaction.commit();

        return products;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getProductByCategoryTypeProducerName
     */
    @Override
    public Optional<Product> getProductByCategoryTypeProducerName(Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Product> optionalProduct = productRepository.getProductByCategoryTypeProducerName(product);

        transaction.commit();

        return optionalProduct;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#setProduct
     */
    @Override
    public Optional<Product> setProduct(Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Product> optionalProduct = productRepository.setProduct(product);

        transaction.commit();

        return optionalProduct;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getProductByValuableFields
     */
    @Override
    public Optional<Product> getProductByValuableFields(Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Product> optionalProduct = productRepository.getProductByValuableFields(product);

        transaction.commit();

        return optionalProduct;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getLastProducts
     */
    @Override
    public List<Product> getLastProducts(int amount, Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Product> products = productRepository.getLastProducts(amount, order);

        transaction.commit();

        return products;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getProductsByCategory
     */
    @Override
    public List<Product> getProductsByCategory(Category category, Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Product> products = productRepository.getProductsByCategory(category, order);

        transaction.commit();

        return products;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#search
     */
    @Override
    public List<Product> search(String search) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Product> products = productRepository.search(search);

        transaction.commit();

        return products;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#getProductsByType
     */
    @Override
    public List<Product> getProductsByType(Type type, Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Product> products = productRepository.getProductsByType(type, order);

        transaction.commit();

        return products;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#checkProductAmount
     */
    @Override
    public boolean checkProductAmount(Product product) {
        Optional<Product> optionalProduct = Optional.ofNullable(product);
        return optionalProduct.filter(value -> value.getAmount() > 0).isPresent();

    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#decreaseProductAmount
     */
    @Override
    public Optional<Product> decreaseProductAmount(Product product, int amount) {
        Optional<Product> optionalProduct = Optional.ofNullable(product);
        if (optionalProduct.isPresent()) {
            if (optionalProduct.get().getAmount() >= amount) {
                optionalProduct = Optional.of(Product.builder()
                        .id(optionalProduct.get().getId())
                        .category(optionalProduct.get().getCategory())
                        .type(optionalProduct.get().getType())
                        .name(optionalProduct.get().getName())
                        .imagePath(optionalProduct.get().getImagePath())
                        .dateInserting(optionalProduct.get().getDateInserting())
                        .producer(optionalProduct.get().getProducer())
                        .amount(optionalProduct.get().getAmount() - amount)
                        .price(optionalProduct.get().getPrice())
                        .build());
                return saveProduct(optionalProduct.get());
            }
        }
        return optionalProduct;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#sortByCategory
     */
    @Override
    public List<Product> sortByCategory(List<Category> categories) {
        List<Product> productsInCategories = new ArrayList<>();
        if (Objects.isNull(categories)) {
            return productsInCategories;
        }
        categories.stream()
                .map(category -> getProductsByCategory(category, Order.ASC))
                .forEachOrdered(productsInCategories::addAll);
        return productsInCategories;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#sortByType
     */
    @Override
    public List<Product> sortByType(List<Type> types) {
        List<Product> productsInTypes = new ArrayList<>();
        if (Objects.isNull(types)) {
            return productsInTypes;
        }
        types.stream()
                .map(type -> getProductsByType(type, Order.ASC))
                .forEachOrdered(productsInTypes::addAll);
        return productsInTypes;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductService#sort
     */
    @Override
    public List<Product> sort(String[] categories, String[] types) {
        List<Category> categoryList = Arrays.stream(categories)
                .map(category -> productRepository.getCategoryRepository().getCategoryByName(category).get())
                .collect(Collectors.toList());
        List<Type> typeList = Arrays.stream(types)
                .map(type -> productRepository.getTypeRepository().getTypeByName(type).get())
                .collect(Collectors.toList());
        List<Product> sortProducts = new ArrayList<>();
        List<Product> productsInCategories = sortByCategory(categoryList);
        List<Product> productsInTypes = sortByType(typeList);
        if (categoryList.isEmpty() && typeList.isEmpty()) {
            return sortProducts;
        } else if (!categoryList.isEmpty()) {
            sortProducts.addAll(productsInCategories);
        }
        if (!typeList.isEmpty()) {
            sortProducts.addAll(productsInTypes);
        }
        Comparator<Product> productComparator = new ProductIdDescComparator();
        sortProducts = sortProducts.stream().distinct().sorted(productComparator).collect(Collectors.toList());
        return sortProducts;
    }
}
