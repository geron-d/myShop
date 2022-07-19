package by.it.academy.services.productInBucket;

import by.it.academy.contants.Constants;
import by.it.academy.contants.Order;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.hiber.productInBucket.ProductInBucketRepository;
import by.it.academy.services.product.ProductService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.services.ProductInBucketService interface.
 *
 * @author Maxim Zhevnov
 */
public class ProductInBucketAPIService implements ProductInBucketService<ProductInBucket> {
    private final Session session;
    private final ProductInBucketRepository<ProductInBucket> productInBucketRepository;
    private final ProductService<Product> productService;

    /**
     * Creates a new {@link ProductInBucketRepository} to manage objects of the given session.
     *
     * @param session                   must not be {@literal null}.
     * @param productInBucketRepository must not be {@literal null}.
     * @param productService            must not be {@literal null}.
     */
    public ProductInBucketAPIService(Session session,
                                     ProductInBucketRepository<ProductInBucket> productInBucketRepository,
                                     ProductService<Product> productService) {
        this.session = session;
        this.productInBucketRepository = productInBucketRepository;
        this.productService = productService;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getProductInBucketById
     */
    @Override
    public Optional<ProductInBucket> getProductInBucketById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<ProductInBucket> productInBucket = productInBucketRepository.getProductInBucketById(id);

        transaction.commit();

        return productInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#saveProductInBucket
     */
    @Override
    public Optional<ProductInBucket> saveProductInBucket(ProductInBucket productInBucket) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<ProductInBucket> optionalProductInBucket = productInBucketRepository
                .saveProductInBucket(productInBucket);

        transaction.commit();

        return optionalProductInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#deleteProductInBucket
     */
    @Override
    public void deleteProductInBucket(ProductInBucket productInBucket) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        productInBucketRepository.deleteProductInBucket(productInBucket);

        transaction.commit();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getAllProductsInBucket
     */
    @Override
    public List<ProductInBucket> getAllProductsInBucket(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<ProductInBucket> productsInBucket = productInBucketRepository
                .getAllProductsInBucket(order);

        transaction.commit();

        return productsInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getProductInBucketByUserProduct
     */
    @Override
    public Optional<ProductInBucket> getProductInBucketByUserProduct(User user, Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<ProductInBucket> optionalProductInBucket = productInBucketRepository
                .getProductInBucketByUserProduct(user, product);

        transaction.commit();

        return optionalProductInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getProductInBucketByValuableFields
     */
    @Override
    public Optional<ProductInBucket> getProductInBucketByValuableFields(User user, Product product) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<ProductInBucket> optionalProductInBucket = productInBucketRepository
                .getProductInBucketByValuableFields(user, product);

        transaction.commit();

        return optionalProductInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getProductInBucketByValuableFields
     */
    @Override
    public Optional<ProductInBucket> getProductInBucketByValuableFields(ProductInBucket productInBucket) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<ProductInBucket> optionalProductInBucket = productInBucketRepository
                .getProductInBucketByValuableFields(productInBucket);

        transaction.commit();

        return optionalProductInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getProductInBucketByUser
     */
    @Override
    public List<ProductInBucket> getProductInBucketByUser(User user) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<ProductInBucket> productsInBucket = productInBucketRepository
                .getProductInBucketByUser(user);

        transaction.commit();

        return productsInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#addAmountToExistProductInBucket
     */
    @Override
    public Optional<ProductInBucket> addAmountToExistProductInBucket(ProductInBucket productInBucket, int n) {
        Optional<ProductInBucket> optionalProductInBucket = Optional.ofNullable(productInBucket);

        if (optionalProductInBucket.isPresent()) {
            optionalProductInBucket = Optional.of(ProductInBucket.builder()
                    .id(optionalProductInBucket.get().getId())
                    .user(optionalProductInBucket.get().getUser())
                    .product(optionalProductInBucket.get().getProduct())
                    .amount(optionalProductInBucket.get().getAmount() + n).build());
            return saveProductInBucket(optionalProductInBucket.get());
        }

        return optionalProductInBucket;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#add
     */
    @Override
    public Optional<ProductInBucket> add(User user, Product product) {
        Optional<ProductInBucket> productInBucket = getProductInBucketByUserProduct(user, product);
        if (productInBucket.isPresent()) {
            return addAmountToExistProductInBucket(productInBucket.get(),
                    Constants.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET);
        } else {
            ProductInBucket productInBucketNew = ProductInBucket.builder()
                    .user(user)
                    .product(product)
                    .amount(Constants.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET).build();
            return saveProductInBucket(productInBucketNew);
        }
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#deleteAmountProducts
     */
    @Override
    public void deleteAmountProducts(List<ProductInBucket> productsInBucket, Product product, int amount) {
        productsInBucket.stream().filter(productInBucket ->
                        Objects.equals(productInBucket.getProduct().getId(), product.getId()))
                .forEach(productInBucket -> {
                    if (productInBucket.getAmount() > amount) {
                        productInBucket.setAmount(productInBucket.getAmount() - amount);
                    } else if (productInBucket.getAmount() == amount) {
                        deleteProductInBucket(productInBucket);
                    }
                });
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#getAllCost
     */
    @Override
    public double getAllCost(List<ProductInBucket> productsInBucket) {
        return productsInBucket.stream()
                .mapToDouble(productInBucket ->
                        productInBucket.getProduct().getPrice() * productInBucket.getAmount()).sum();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#deleteAllProducts
     */
    @Override
    public void deleteAllProducts(List<ProductInBucket> productsInBucket) {
        productsInBucket.forEach(this::deleteProductInBucket);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#productsInBucket
     */
    @Override
    public void buy(List<ProductInBucket> productsInBucket) {
        productsInBucket.forEach(productInBucket -> {
            productService.decreaseProductAmount(productInBucket.getProduct(), productInBucket.getAmount());
            deleteProductInBucket(productInBucket);
        });
    }
}
