package by.it.academy.services.bucket;

import by.it.academy.Paths;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.bucket.BucketRepository;
import by.it.academy.services.product.ProductService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class BucketAPIService implements BucketService<Bucket> {
    BucketRepository<Bucket> bucketRepository;
    ProductService<Product> productService;

    public BucketAPIService(BucketRepository<Bucket> bucketRepository, ProductService<Product> productService) {
        this.bucketRepository = bucketRepository;
        this.productService = productService;
    }

    @Override
    public boolean create(Bucket bucket) {
        return bucketRepository.create(bucket);
    }

    @Override
    public Bucket get(Bucket bucket) {
        return bucketRepository.get(bucket);
    }

    @Override
    public boolean update(Bucket bucket, Bucket newBucket) {
        return bucketRepository.update(bucket, newBucket);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketRepository.delete(bucket);
    }

    @Override
    public List<Bucket> getAll() {
        return bucketRepository.getAll();
    }

    @Override
    public Bucket getByUserAndProduct(User user, Product product) {
        return bucketRepository.getByUserAndProduct(user, product);
    }

    @Override
    public Bucket getById(int id) {
        return bucketRepository.getById(id);
    }

    public boolean addAmountToExistBucket(Bucket bucket, int n) {
        Bucket newBucket = new Bucket(bucket.getId(), bucket.getUserId(), bucket.getProductId(), bucket.getAmount() + n);
        return update(bucket, newBucket);
    }

    @Override
    public boolean add(User user, Product product) {
        Bucket bucket = getByUserAndProduct(user, product);
        if (bucket.getId() > 0) {
            return addAmountToExistBucket(bucket, Paths.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET);
        } else {
            Bucket newBucket = new Bucket(user.getId(), product.getId(), Paths.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET);
            return create(newBucket);
        }
    }

    @Override
    public List<Bucket> getByUser(User user) {
        return bucketRepository.getByUser(user);
    }

    @Override
    public List<ProductInBucket> getProductsInBucket(User user) {
        List<Bucket> bucket = getByUser(user);

        return bucket
                .stream()
                .map(bucket1 -> new ProductInBucket(productService.getByID(bucket1.getProductId()), bucket1.getId(), bucket1.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAmountProducts(List<ProductInBucket> productsInBucket, Product product, int amount) {
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        productsInBucket
                .stream()
                .filter(productInBucket -> productInBucket.getProduct().getId() == product.getId())
                .forEach(
                        productInBucket -> {
                            if (productInBucket.getAmount() > amount) {
                                Bucket bucket = getById(productInBucket.getBucketId());
                                bucket.setAmount(productInBucket.getAmount() - amount);
                                isDeleted.set(update(bucket, bucket));
                            } else if (productInBucket.getAmount() == amount) {
                                Bucket bucket = getById(productInBucket.getBucketId());
                                isDeleted.set(delete(bucket));
                            }
                        }
                );
        return isDeleted.get();
    }

    @Override
    public double getAllCost(List<ProductInBucket> productsInBucket) {
        return productsInBucket
                .stream().mapToDouble(productInBucket -> productInBucket.getProduct().getPrice() * productInBucket.getAmount()).sum();
    }

    @Override
    public boolean deleteAllProducts(List<ProductInBucket> productsInBucket) {
        AtomicBoolean isDeletedAll = new AtomicBoolean(true);
        productsInBucket
                .forEach(productInBucket -> {
                    boolean isDeleted = delete(getById(productInBucket.getBucketId()));
                    if (!isDeleted) {
                        isDeletedAll.set(false);
                    }
                });
        return isDeletedAll.get();
    }

    @Override
    public boolean buy(List<ProductInBucket> productsInBucket) {
        AtomicBoolean isBought = new AtomicBoolean(true);
        productsInBucket.forEach(productInBucket -> {
            boolean isDecreased = productService.decreaseProductAmount(productInBucket.getProduct(), productInBucket.getAmount());
            if (!isDecreased) {
                isBought.set(false);
            }
            boolean isDeleted = delete(getById(productInBucket.getBucketId()));
            if (!isDeleted) {
                isBought.set(false);
            }
        });
        return isBought.get();
    }

}
