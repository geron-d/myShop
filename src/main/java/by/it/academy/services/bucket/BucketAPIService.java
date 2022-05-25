package by.it.academy.services.bucket;

import by.it.academy.contants.Constants;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.bucket.BucketRepository;
import by.it.academy.services.product.ProductService;

import java.util.List;
import java.util.Optional;
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
        return bucketRepository.get(bucket).isPresent()
                ? bucketRepository.get(bucket).get()
                : new Bucket();
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
    public List<Bucket> getAllBucket() {
        return bucketRepository.getAllBucket()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Bucket getByUserAndProduct(User user, Product product) {
        return bucketRepository.getByUserAndProduct(user, product).isPresent()
                ? bucketRepository.getByUserAndProduct(user, product).get()
                : new Bucket();
    }

    @Override
    public Bucket getById(int id) {
        return bucketRepository.getById(id).isPresent()
                ? bucketRepository.getById(id).get()
                : new Bucket();
    }

    public boolean addAmountToExistBucket(Bucket bucket, int n) {
        Bucket newBucket = Bucket.builder().id(bucket.getId()).userId(bucket.getUserId()).productId(bucket.getProductId()).amount(bucket.getAmount() + n).build();
        return update(bucket, newBucket);
    }

    @Override
    public boolean add(User user, Product product) {
        Bucket bucket = getByUserAndProduct(user, product);
        if (isBucketExist(bucket)) {
            return addAmountToExistBucket(bucket, Constants.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET);
        } else {
            Bucket newBucket = Bucket.builder().userId(user.getId()).productId(product.getId()).amount(Constants.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET).build();
            return create(newBucket);
        }
    }

    @Override
    public List<Bucket> getByUser(User user) {
        return bucketRepository.getByUser(user)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductInBucket> getProductsInBucket(User user) {
        List<Bucket> bucket = getByUser(user);

        return bucket.stream().map(alsoBucket -> ProductInBucket.builder().product(productService.getByID(alsoBucket.getProductId())).bucketId(alsoBucket.getId()).amount(alsoBucket.getAmount()).build()).collect(Collectors.toList());

    }

    @Override
    public boolean deleteAmountProducts(List<ProductInBucket> productsInBucket, Product product, int amount) {
        AtomicBoolean isProductsAmountDeleted = new AtomicBoolean(false);
        productsInBucket.stream().filter(productInBucket -> productInBucket.getProduct().getId() == product.getId()).forEach(productInBucket -> {
            if (productInBucket.getAmount() > amount) {
                Bucket bucket = getById(productInBucket.getBucketId());
                bucket.setAmount(productInBucket.getAmount() - amount);
                isProductsAmountDeleted.set(update(bucket, bucket));
            } else if (productInBucket.getAmount() == amount) {
                Bucket bucket = getById(productInBucket.getBucketId());
                isProductsAmountDeleted.set(delete(bucket));
            }
        });
        return isProductsAmountDeleted.get();
    }

    @Override
    public double getAllCost(List<ProductInBucket> productsInBucket) {
        return productsInBucket.stream().mapToDouble(productInBucket -> productInBucket.getProduct().getPrice() * productInBucket.getAmount()).sum();
    }

    @Override
    public boolean deleteAllProducts(List<ProductInBucket> productsInBucket) {
        AtomicBoolean isDeletedAll = new AtomicBoolean(true);
        productsInBucket.forEach(productInBucket -> {
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

    private boolean isBucketExist(Bucket bucket) {
        return bucket.getId() > 0;
    }

}
