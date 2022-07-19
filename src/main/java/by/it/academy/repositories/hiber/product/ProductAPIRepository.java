package by.it.academy.repositories.hiber.product;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import by.it.academy.repositories.hiber.type.TypeRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductAPIRepository implements ProductRepository<Product> {
    Logger log = Logger.getLogger(ProductAPIRepository.class);
    private final Session session;
    private final CategoryRepository<Category> categoryRepository;
    private final TypeRepository<Type> typeRepository;
    private final ProducerRepository<Producer> producerRepository;

    public ProductAPIRepository(Session session, CategoryRepository<Category> categoryRepository,
                                TypeRepository<Type> typeRepository, ProducerRepository<Producer> producerRepository) {
        this.session = session;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        Optional<Product> product = Optional.empty();
        try {
            product = Optional.of(session.get(Product.class, id));
            log.info("ProductAPIRepository - method: getProductById: " + product);
        } catch (NullPointerException e) {
            log.info("ProductAPIRepository - method: getProductById: " + e);
            return product;
        }
        return product;
    }

    @Override
    public Optional<Product> saveProduct(Product product) {
        Optional<Product> optionalProduct = setProduct(product);

        if (optionalProduct.isPresent()) {
            if ((Objects.isNull(optionalProduct.get().getId())
                    || !getProductById(optionalProduct.get().getId()).isPresent())
                    && !getProductByValuableFields(optionalProduct.get()).isPresent()) {
                session.persist(optionalProduct.get());
                log.info("ProductAPIRepository - method: saveProduct: persist: " + optionalProduct);
            } else if (!getProductByValuableFields(optionalProduct.get()).isPresent()) {
                session.merge(optionalProduct.get());
                log.info("ProductAPIRepository - method: saveProduct: merge: " + optionalProduct);
            } else {
                log.info("ProductAPIRepository - method: saveProduct: product is already exist");
            }
        } else {
            log.info("ProductAPIRepository - method: saveProduct: product is null");
        }

        return getProductByValuableFields(product);
    }

    @Override
    public void deleteProduct(Product product) {
        Optional<Product> optionalProduct = Optional.ofNullable(product);

        if (optionalProduct.isPresent() && Objects.nonNull(optionalProduct.get().getId())) {
            session.delete(optionalProduct.get());
            log.info("ProductAPIRepository - method: deleteProduct: remove: " + product);
        } else {
            log.info("ProductAPIRepository - method: deleteProduct: product doesn't exist");
        }
    }

    @Override
    public List<Product> getAllProducts(Order order) {
        List<Product> products;

        TypedQuery<Product> query = session.createQuery(HQL.GET_ALL_PRODUCTS_HQL, Product.class);
        TypedQuery<Product> queryDesc = session.createQuery(HQL.GET_ALL_PRODUCTS_DESC_HQL, Product.class);
        products = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("ProductAPIRepository - method: getAllProducts: " + products);

        return products;
    }

    @Override
    public Optional<Product> getProductByCategoryTypeProducerName(Product product) {
        Optional<Product> optionalProduct = Optional.ofNullable(product);

        if (optionalProduct.isPresent()
                && Objects.nonNull(optionalProduct.get().getCategory())
                && Objects.nonNull(optionalProduct.get().getType())
                && Objects.nonNull(optionalProduct.get().getProducer())) {
            if (Objects.isNull(optionalProduct.get().getCategory().getId())
                    || Objects.isNull(optionalProduct.get().getType().getId())
                    || Objects.isNull(optionalProduct.get().getProducer().getProducer())) {
                optionalProduct = setProduct(optionalProduct.get());
            }
        } else {
            log.info("ProductAPIRepository - method: getProductByCategoryTypeProducerName: product is null");
            return Optional.empty();
        }

        try {
            TypedQuery<Product> query = session.createQuery(HQL.GET_PRODUCT_BY_CATEGORY_TYPE_PRODUCER_NAME,
                    Product.class);
            if (optionalProduct.isPresent()) {
                query.setParameter("category", optionalProduct.get().getCategory());
                query.setParameter("type", optionalProduct.get().getType());
                query.setParameter("name", optionalProduct.get().getName());
                query.setParameter("producer", optionalProduct.get().getProducer());
                optionalProduct = Optional.of(query.getSingleResult());
            }
            log.info("ProductAPIRepository - method: getProductByCategoryTypeProducerName: " + optionalProduct);
        } catch (NoResultException e) {
            log.info("ProductAPIRepository - method: getProductByCategoryTypeProducerName: " + e);
            return Optional.empty();
        }
        return optionalProduct;
    }

    @Override
    public Optional<Product> setProduct(Product product) {
        Optional<Product> optionalProduct = Optional.ofNullable(product);

        if (optionalProduct.isPresent()) {
            Optional<Category> category = categoryRepository
                    .getCategoryByValuableFields(optionalProduct.get().getCategory());
            category
                    .ifPresentOrElse(optionalProduct.get()::setCategory,
                            () -> categoryRepository.saveCategory(optionalProduct.get().getCategory()));

            Optional<Type> type = typeRepository.getTypeByValuableFields(optionalProduct.get().getType());
            type
                    .ifPresentOrElse(optionalProduct.get()::setType,
                            () -> typeRepository.saveType(optionalProduct.get().getType()));

            Optional<Producer> producer = producerRepository
                    .getProducerByValuableFields(optionalProduct.get().getProducer());
            producer
                    .ifPresentOrElse(optionalProduct.get()::setProducer,
                            () -> producerRepository.saveProducer(optionalProduct.get().getProducer()));
        }

        return optionalProduct;
    }

    @Override
    public Optional<Product> getProductByValuableFields(Product product) {
        Optional<Product> optionalProduct = Optional.ofNullable(product);
        if (optionalProduct.isPresent()) {
            return getProductByCategoryTypeProducerName(optionalProduct.get());
        } else {
            return optionalProduct;
        }
    }

    @Override
    public List<Product> getLastProducts(int amount, Order order) {
        List<Product> products;

        TypedQuery<Product> query = session.createQuery(HQL.GET_ALL_PRODUCTS_HQL, Product.class);
        TypedQuery<Product> queryDesc = session.createQuery(HQL.GET_ALL_PRODUCTS_DESC_HQL, Product.class);
        query.setMaxResults(amount);
        queryDesc.setMaxResults(amount);
        products = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("ProductAPIRepository - method: getLastProducts: " + products);

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Category category, Order order) {
        List<Product> products = new ArrayList<>();

        Optional<Category> optionalCategory = categoryRepository
                .getCategoryByValuableFields(category);

        if (optionalCategory.isPresent()) {
            TypedQuery<Product> query = session.createQuery(HQL.GET_PRODUCTS_BY_CATEGORY_HQL, Product.class);
            TypedQuery<Product> queryDesc = session.createQuery(HQL.GET_PRODUCTS_BY_CATEGORY_DESC_HQL, Product.class);
            query.setParameter("category", optionalCategory.get());
            queryDesc.setParameter("category", optionalCategory.get());
            products = order.equals(Order.ASC)
                    ? query.getResultList()
                    : queryDesc.getResultList();
            log.info("ProductAPIRepository - method: getProductsByCategory: " + products);
        }

        return products;
    }

    @Override
    public List<Product> search(String search) {
        List<Product> products;

        TypedQuery<Product> query = session.createQuery(HQL.PRODUCTS_SEARCH_HQL, Product.class);
        query.setParameter("search", "%" + search + "%");
        products = query.getResultList();

        log.info("ProductAPIRepository - method: search: " + products);

        return products;
    }

    @Override
    public List<Product> getProductsByType(Type type, Order order) {
        List<Product> products = new ArrayList<>();

        Optional<Type> optionalType = typeRepository
                .getTypeByValuableFields(type);

        if (optionalType.isPresent()) {
            TypedQuery<Product> query = session.createQuery(HQL.GET_PRODUCTS_BY_TYPE_HQL, Product.class);
            TypedQuery<Product> queryDesc = session.createQuery(HQL.GET_PRODUCTS_BY_TYPE_DESC_HQL, Product.class);
            query.setParameter("type", optionalType.get());
            queryDesc.setParameter("type", optionalType.get());
            products = order.equals(Order.ASC)
                    ? query.getResultList()
                    : queryDesc.getResultList();
            log.info("ProductAPIRepository - method: getProductsByType: " + products);
        }

        return products;
    }

    @Override
    public CategoryRepository<Category> getCategoryRepository() {
        return categoryRepository;
    }

    @Override
    public TypeRepository<Type> getTypeRepository() {
        return typeRepository;
    }

    @Override
    public ProducerRepository<Producer> getProducerRepository() {
        return producerRepository;
    }
}
