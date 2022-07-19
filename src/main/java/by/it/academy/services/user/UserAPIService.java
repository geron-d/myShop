package by.it.academy.services.user;

import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.dtos.requests.productInBucket.ProductInBucketDTO;
import by.it.academy.dtos.requests.productInBucket.ProductInBucketDeleteDTO;
import by.it.academy.dtos.requests.productInBucket.ProductInBucketWithIdDTO;
import by.it.academy.dtos.requests.user.UserDTO;
import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.exceptions.product.HaveNotEnoughProductException;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.productInBucket.ProductInBucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAPIService implements UserService<User> {

    private final UserRepository userRepository;

    private final ProductService<Product> productService;

    private final ProductInBucketService<ProductInBucket> productInBucketService;

    @Override
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Long createUser(UserDTO dto) {
        User user = buildUser(dto);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public Long updateUser(Long id, UserDTO dto) {
        if (!checkUser(id)) {
            throw new NoSuchElementException();
        }
        User user = buildUser(dto);
        user.setId(id);
        user.setBucket(getProductsInBucket(id));
        return userRepository.save(user).getId();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUser(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findUser(UserDTO dto) {
        return userRepository.findUserByLoginAndPassword(dto.getLogin(), dto.getPassword());
    }

    @Override
    public boolean checkUser(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public boolean checkUser(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<ProductInBucket> getProductsInBucket(Long id) {
        return findUser(id).getBucket();
    }

    @Override
    @Transactional
    public Long addProductInBucket(ProductInBucketWithIdDTO dto) {
        User user = findUser(dto.getUserId());
        Product product = productService.findProduct(dto.getProductId());
        ProductInBucketDTO productInBucketDTO = ProductInBucketDTO.builder()
                .user(user)
                .product(product)
                .amount(dto.getAmount())
                .build();
        Long productInBucketId = productInBucketService.addProductInBucket(productInBucketDTO);
        ProductInBucket productInBucket = productInBucketService.findProductInBucket(productInBucketId);
        user.getBucket().add(productInBucket);
        return productInBucketId;
    }

    @Override
    @Transactional
    public void deleteProductInBucket(ProductInBucketDeleteDTO dto) {
        User user = findUser(dto.getUserId());
        Product product = productService.findProduct(dto.getProductId());
        ProductInBucket productInBucket = productInBucketService.findProductInBucket(user, product);
        user.getBucket().remove(productInBucket);
        productInBucketService.deleteProductInBucket(productInBucket.getId());
    }

    @Override
    @Transactional
    public Long decreaseProductInBucket(ProductInBucketWithIdDTO dto) {
        User user = findUser(dto.getUserId());
        Product product = productService.findProduct(dto.getProductId());
        ProductInBucket productInBucket = productInBucketService.findProductInBucket(user, product);
        if (!productInBucketService.checkAmountProductInBucket(productInBucket, dto.getAmount())) {
            throw new HaveNotEnoughProductException("haven't enough product");
        }
        productInBucket.setAmount(productInBucket.getAmount() - dto.getAmount());
        return productInBucketService.updateProductInBucket(productInBucket);
    }

    @Override
    @Transactional
    public void deleteProductsInBucket(Long id) {
        User user = findUser(id);
        productInBucketService.deleteProductsInBucket(user);
    }

    @Override
    @Transactional
    public double getCostProductsInBucket(Long id) {
        List<ProductInBucket> productsInBucket = getProductsInBucket(id);
        return productsInBucket.stream()
                .mapToDouble(productInBucket -> productInBucket.getProduct().getPrice() * productInBucket.getAmount())
                .sum();
    }

    @Override
    @Transactional
    public void buyProductsInBucket(Long id) {
        List<ProductInBucket> productsInBucket = getProductsInBucket(id);
        productsInBucket
                .stream()
                .map(productInBucket -> ProductDecreaseRequest.builder()
                        .productId(productInBucket.getProduct().getId())
                        .amount(productInBucket.getAmount())
                        .build())
                .forEach(productService::decreaseProductAmount);
        deleteProductsInBucket(id);
    }

    private User buildUser(UserDTO dto) {
        return User.builder()
                .login(dto.getLogin())
                .password((dto.getPassword()))
                .accessLevel(AccessLevel.USER)
                .build();
    }

}
