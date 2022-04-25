package by.it.academy.services.bucket;

import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;

import java.util.List;

public interface BucketService {
    List<ProductInBucket> addProduct (List<ProductInBucket> bucket, Product product);

    List<ProductInBucket> deleteProduct(List<ProductInBucket> bucket, Product product);
}
