package by.it.academy.services.bucket;

import by.it.academy.Paths;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.bucket.BucketRepository;
import by.it.academy.services.product.ProductService;

import java.util.ArrayList;
import java.util.List;

public class BucketAPIService implements BucketService<Bucket>{
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

    public boolean addAmountToExistBucket (Bucket bucket, int n) {
        Bucket newBucket = new Bucket(bucket.getId(), bucket.getUserId(), bucket.getProductId(), bucket.getAmount() + n);
        return update(bucket, newBucket);
    }

    @Override
    public boolean add(User user, Product product) {
        Bucket bucket = getByUserAndProduct(user,product);
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
        List<ProductInBucket> productsInBucket = new ArrayList<>();
        List<Bucket> bucket = getByUser(user);
        for (Bucket value : bucket) {
            Product product = productService.getByID(value.getProductId());
            ProductInBucket productInBucket = new ProductInBucket(product, value.getId(), value.getAmount());
            productsInBucket.add(productInBucket);
        }
        return productsInBucket;
    }

    @Override
    public boolean deleteAmountProducts(List<ProductInBucket> productsInBucket, Product product, int amount) {
        for (ProductInBucket productInBucket : productsInBucket) {
            if (productInBucket.getProduct().getId() == product.getId()) {
                if (productInBucket.getAmount() > amount) {
                    Bucket bucket = getById(productInBucket.getBucketId());
                    bucket.setAmount(productInBucket.getAmount() - amount);
                    return update(bucket, bucket);
                } else if (productInBucket.getAmount() == amount) {
                    Bucket bucket = getById(productInBucket.getBucketId());
                    return delete(bucket);
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public double getAllCost(List<ProductInBucket> productsInBucket) {
        double allCost = 0;
        for (ProductInBucket productInBucket : productsInBucket) {
            double price = productInBucket.getProduct().getPrice();
            int amount = productInBucket.getAmount();
            allCost += price * amount;
        }
        return allCost;
    }

    @Override
    public boolean deleteAllProducts(List<ProductInBucket> productsInBucket) {
        boolean isDeletedAll = true;
        for (ProductInBucket productInBucket : productsInBucket) {
            Bucket bucket = getById(productInBucket.getBucketId());
            boolean isDeleted = delete(bucket);
            if (!isDeleted) {
                return false;
            }
        }
        return isDeletedAll;
    }

    @Override
    public boolean buy(List<ProductInBucket> productsInBucket) {
        boolean isBought = true;
        for (ProductInBucket productInBucket : productsInBucket) {
            boolean isDecreased = productService.decreaseProductAmount(productInBucket.getProduct(), productInBucket.getAmount());
            if (!isDecreased) {
                return false;
            }
            Bucket bucket = getById(productInBucket.getBucketId());
            boolean isDeleted = delete(bucket);
        }
        return isBought;
    }

}
