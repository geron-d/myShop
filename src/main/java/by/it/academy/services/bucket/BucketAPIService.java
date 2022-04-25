package by.it.academy.services.bucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;

import java.util.List;

public class BucketAPIService implements BucketService{

    @Override
    public List<ProductInBucket> addProduct (List<ProductInBucket> bucket, Product product) {
        ProductInBucket productInBucket = new ProductInBucket(product);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getProduct().equals(product)) {
                bucket.get(i).setAmount(bucket.get(i).getAmount() + 1);
                return bucket;
            }
        }
        bucket.add(productInBucket);
        return bucket;
    }

    @Override
    public List<ProductInBucket> deleteProduct(List<ProductInBucket> bucket, Product product) {
        ProductInBucket productInBucket = new ProductInBucket(product);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getProduct().equals(product)) {
                if (bucket.get(i).getAmount() == 0) {
                    bucket.remove(productInBucket);
                    return bucket;
                }
                bucket.get(i).setAmount(bucket.get(i).getAmount() - 1);
                return bucket;
            }
        }
        return bucket;
    }
}
