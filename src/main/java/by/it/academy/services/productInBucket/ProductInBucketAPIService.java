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

/**
 * Implementation of the by.it.academy.services.ProductInBucketService interface.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInBucketAPIService implements ProductInBucketService {

    private final ProductInBucketRepository productInBucketRepository;

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#findProductInBucket(Long id)
     */
    @Override
    public ProductInBucket findProductInBucket(Long id) {
        return productInBucketRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#createProductInBucket(ProductInBucket productInBucket)
     */
    @Override
    @Transactional
    public Long createProductInBucket(ProductInBucket productInBucket) {
        if (checkProductInBucket(productInBucket.getUser(), productInBucket.getProduct())) {
            throw new EntityExistsException("such product in user bucket exists");
        }
        return productInBucketRepository.save(productInBucket).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#updateProductInBucket(ProductInBucket productInBucket)
     */
    @Override
    @Transactional
    public Long updateProductInBucket(ProductInBucket productInBucket) {
        if (!checkProductInBucket(productInBucket.getId())) {
            throw new NoSuchElementException("such product in user bucket doesn't exist");
        }
        return productInBucketRepository.save(productInBucket).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#deleteProductInBucket(Long id)
     */
    @Override
    @Transactional
    public void deleteProductInBucket(Long id) {
        ProductInBucket productInBucket = findProductInBucket(id);
        productInBucket.setUser(null);
        productInBucket.setProduct(null);
        updateProductInBucket(productInBucket);
        productInBucketRepository.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#addProductInBucket(ProductInBucketDTO dto)
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#findProductInBucket(User user, Product product)
     */
    @Override
    public ProductInBucket findProductInBucket(User user, Product product) {
        return productInBucketRepository.findByUserAndProduct(user, product).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#checkAmountProductInBucket(ProductInBucket productInBucket,
     * int amount)
     */
    @Override
    public boolean checkAmountProductInBucket(ProductInBucket productInBucket, int amount) {
        return productInBucket.getAmount() > amount;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#deleteProductsInBucket(User user)
     */
    @Override
    @Transactional
    public void deleteProductsInBucket(User user) {
        List<ProductInBucket> productsInBucket = findProductsInBucket(user);
        productsInBucket.forEach(this::deleteProductInBucket);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#findProductsInBucket(User user)
     */
    @Override
    public List<ProductInBucket> findProductsInBucket(User user) {
        return productInBucketRepository.findAllByUser(user);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProductInBucketService#deleteProductInBucket(ProductInBucket productInBucket)
     */
    @Override
    public void deleteProductInBucket(ProductInBucket productInBucket) {
        productInBucket.setUser(null);
        productInBucket.setProduct(null);
        updateProductInBucket(productInBucket);
        productInBucketRepository.deleteById(productInBucket.getId());
    }

    /**
     * Check productInBucket by given user and product.
     *
     * @return true if productInBucket exists and false when does not.
     */
    private boolean checkProductInBucket(User user, Product product) {
        return productInBucketRepository.existsByUserAndProduct(user, product);
    }

    /**
     * Check productInBucket by given id.
     *
     * @return true if productInBucket exists and false when does not.
     */
    private boolean checkProductInBucket(Long id) {
        return productInBucketRepository.existsById(id);
    }

    /**
     * Build productInBucket by ProductInBucketDTO.
     *
     * @return productInBucket by ProductInBucketDTO.
     */
    private ProductInBucket buildProductInBucket(ProductInBucketDTO dto) {
        return ProductInBucket.builder()
                .user(dto.getUser())
                .product(dto.getProduct())
                .amount(dto.getAmount())
                .build();
    }

}
