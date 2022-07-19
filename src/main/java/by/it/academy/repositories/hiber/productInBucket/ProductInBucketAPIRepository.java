package by.it.academy.repositories.hiber.productInBucket;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductInBucketAPIRepository implements ProductInBucketRepository<ProductInBucket> {
    Logger log = Logger.getLogger(ProductInBucketAPIRepository.class);
    private final Session session;

    public ProductInBucketAPIRepository(Session session) {
        this.session = session;
    }

    @Override
    public Optional<ProductInBucket> getProductInBucketById(int id) {
        Optional<ProductInBucket> productInBucket = Optional.empty();
        try {
            productInBucket = Optional.of(session.get(ProductInBucket.class, id));
            log.info("ProductInBucketAPIRepository - method: getProductInBucketById: " + productInBucket);
        } catch (NullPointerException e) {
            log.info("ProductInBucketAPIRepository - method: getProductInBucketById: " + e);
            return productInBucket;
        }
        return productInBucket;
    }

    @Override
    public Optional<ProductInBucket> saveProductInBucket(ProductInBucket productInBucket) {
        Optional<ProductInBucket> optionalProductInBucket = Optional.ofNullable(productInBucket);

        if (optionalProductInBucket.isPresent()) {
            if ((Objects.isNull(optionalProductInBucket.get().getId())
                    || !getProductInBucketById(optionalProductInBucket.get().getId()).isPresent())
                    && !getProductInBucketByValuableFields(optionalProductInBucket.get()).isPresent()) {
                session.persist(optionalProductInBucket.get());
                log.info("ProductInBucketAPIRepository - method: saveProductInBucket: persist: " + optionalProductInBucket);
            } else if (!getProductInBucketByValuableFields(optionalProductInBucket.get()).isPresent()) {
                session.merge(optionalProductInBucket.get());
                log.info("ProductInBucketAPIRepository - method: saveProductInBucket: merge: " + optionalProductInBucket);
            } else {
                log.info("ProductInBucketAPIRepository - method: saveProductInBucket: productInBucket is already exist");
            }
        } else {
            log.info("ProductInBucketAPIRepository - method: saveProductInBucket: productInBucket is null");
        }

        return getProductInBucketByValuableFields(productInBucket);
    }

    @Override
    public void deleteProductInBucket(ProductInBucket productInBucket) {
        Optional<ProductInBucket> optionalProductInBucket = Optional.ofNullable(productInBucket);

        if (optionalProductInBucket.isPresent() && Objects.nonNull(optionalProductInBucket.get().getId())) {
            session.delete(optionalProductInBucket.get());
            log.info("ProductInBucketAPIRepository - method: deleteProductInBucket: remove: " + optionalProductInBucket);
        } else {
            log.info("ProductInBucketAPIRepository - method: deleteProductInBucket: productInBucket doesn't exist");
        }
    }

    @Override
    public List<ProductInBucket> getAllProductsInBucket(Order order) {
        List<ProductInBucket> productsInBucket;

        TypedQuery<ProductInBucket> query = session.createQuery(HQL.GET_ALL_PRODUCTS_IN_BUCKET_HQL,
                ProductInBucket.class);
        TypedQuery<ProductInBucket> queryDesc = session.createQuery(HQL.GET_ALL_PRODUCTS_IN_BUCKET_DESC_HQL,
                ProductInBucket.class);
        productsInBucket = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("ProductInBucketAPIRepository - method: getAllProductsInBucket: " + productsInBucket);

        return productsInBucket;
    }

    @Override
    public Optional<ProductInBucket> getProductInBucketByUserProduct(User user, Product product) {
        Optional<ProductInBucket> productInBucket = Optional.empty();
        try {
            TypedQuery<ProductInBucket> query = session.createQuery(HQL.GET_PRODUCTS_IN_BUCKET_BY_USER_PRODUCT_HQL,
                    ProductInBucket.class);
            query.setParameter("user", user);
            query.setParameter("product", product);
            productInBucket = Optional.of(query.getSingleResult());
            log.info("ProductInBucketAPIRepository - method: getProductInBucketByUserProduct: " + productInBucket);
        } catch (NoResultException e) {
            log.info("ProductInBucketAPIRepository - method: getProductInBucketByUserProduct: " + e);
            return productInBucket;
        }
        return productInBucket;
    }

    @Override
    public Optional<ProductInBucket> getProductInBucketByValuableFields(User user, Product product) {
        return getProductInBucketByUserProduct(user, product);
    }

    @Override
    public Optional<ProductInBucket> getProductInBucketByValuableFields(ProductInBucket productInBucket) {
        Optional<ProductInBucket> optionalProductInBucket = Optional.ofNullable(productInBucket);
        if (optionalProductInBucket.isPresent()) {
            return getProductInBucketByUserProduct(optionalProductInBucket.get().getUser(),
                    optionalProductInBucket.get().getProduct());
        } else {
            return optionalProductInBucket;
        }
    }

    @Override
    public List<ProductInBucket> getProductInBucketByUser(User user) {
        List<ProductInBucket> productsInBucket;

        TypedQuery<ProductInBucket> query = session.createQuery(HQL.GET_PRODUCTS_IN_BUCKET_BY_USER_HQL,
                ProductInBucket.class);
        query.setParameter("user", user);
        productsInBucket = query.getResultList();
        log.info("ProductInBucketAPIRepository - method: getProductInBucketByUser: " + productsInBucket);

        return productsInBucket;
    }

    public Session getSession() {
        return session;
    }
}
