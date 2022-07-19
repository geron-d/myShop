package by.it.academy.services.productInBucket;

import by.it.academy.dtos.requests.productInBucket.ProductInBucketDTO;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.productInBucket.ProductInBucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInBucketAPIService implements ProductInBucketService<ProductInBucket> {

    private final ProductInBucketRepository productInBucketRepository;

    @Override
    public ProductInBucket findProductInBucket(Long id) {
        return productInBucketRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Long createProductInBucket(ProductInBucketDTO dto) {
        if (checkProductInBucket(dto.getUser(), dto.getProduct())) {
            throw new EntityExistsException("such product in user bucket exists");
        }
        ProductInBucket productInBucket = buildProductInBucket(dto);
        return productInBucketRepository.save(productInBucket).getId();
    }

    @Override
    @Transactional
    public Long createProductInBucket(ProductInBucket productInBucket) {
        if (checkProductInBucket(productInBucket.getUser(), productInBucket.getProduct())) {
            throw new EntityExistsException("such product in user bucket exists");
        }
        return productInBucketRepository.save(productInBucket).getId();
    }

    @Override
    @Transactional
    public Long updateProductInBucket(ProductInBucket productInBucket) {
        if (!checkProductInBucket(productInBucket.getId())) {
            throw new NoSuchElementException("such product in user bucket doesn't exist");
        }
        return productInBucketRepository.save(productInBucket).getId();
    }

    @Override
    @Transactional
    public Long updateProductInBucket(Long id, ProductInBucketDTO dto) {
        if (!checkProductInBucket(id)) {
            throw new NoSuchElementException("such product in user bucket doesn't exist");
        }
        ProductInBucket productInBucket = buildProductInBucket(dto);
        productInBucket.setId(id);
        return productInBucketRepository.save(productInBucket).getId();
    }

    @Override
    @Transactional
    public void deleteProductInBucket(Long id) {
        ProductInBucket productInBucket = findProductInBucket(id);
        productInBucket.setUser(null);
        productInBucket.setProduct(null);
        updateProductInBucket(productInBucket);
        productInBucketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Long addProductInBucket(ProductInBucketDTO dto) {
        ProductInBucket productInBucket = buildProductInBucket(dto);
        if (checkProductInBucket(dto.getUser(), dto.getProduct())) {
            productInBucket = findProductInBucket(productInBucket.getUser(), productInBucket.getProduct());
            return updateProductInBucket(productInBucket);
        } else {
            return createProductInBucket(productInBucket);
        }
    }

    @Override
    @Transactional
    public Long addProductInBucket(ProductInBucket productInBucket) {
        if (checkProductInBucket(productInBucket.getId())) {
            return updateProductInBucket(productInBucket);
        } else {
            return createProductInBucket(productInBucket);
        }
    }

    @Override
    public ProductInBucket findProductInBucket(User user, Product product) {
        return productInBucketRepository.findByUserAndProduct(user, product);
    }

    @Override
    public boolean checkAmountProductInBucket(ProductInBucket productInBucket, int amount) {
        return productInBucket.getAmount() > amount;
    }

    @Override
    @Transactional
    public void deleteProductsInBucket(User user) {
        List<ProductInBucket> productsInBucket = findProductsInBucket(user);
        productsInBucket.forEach(this::deleteProductInBucket);
    }

    @Override
    public List<ProductInBucket> findProductsInBucket(User user) {
        return productInBucketRepository.findAllByUser(user);
    }

    @Override
    public void deleteProductInBucket(ProductInBucket productInBucket) {
        productInBucket.setUser(null);
        productInBucket.setProduct(null);
        updateProductInBucket(productInBucket);
        productInBucketRepository.deleteById(productInBucket.getId());
    }

    private boolean checkProductInBucket(User user, Product product) {
        return productInBucketRepository.existsByUserAndProduct(user, product);
    }

    private boolean checkProductInBucket(Long id) {
        return productInBucketRepository.existsById(id);
    }

    private ProductInBucket buildProductInBucket(ProductInBucketDTO dto) {
        return ProductInBucket.builder()
                .user(dto.getUser())
                .product(dto.getProduct())
                .amount(dto.getAmount())
                .build();
    }

}
