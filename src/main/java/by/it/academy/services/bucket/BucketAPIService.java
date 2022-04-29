package by.it.academy.services.bucket;

import by.it.academy.Paths;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.bucket.BucketRepository;

import java.util.List;

public class BucketAPIService implements BucketService<Bucket>{
    BucketRepository<Bucket> bucketRepository;

    public BucketAPIService(BucketRepository<Bucket> bucketRepository) {
        this.bucketRepository = bucketRepository;
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
            return create(bucket);
        }
    }
}
