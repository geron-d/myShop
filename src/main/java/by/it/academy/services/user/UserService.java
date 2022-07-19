package by.it.academy.services.user;

import by.it.academy.dtos.requests.productInBucket.ProductInBucketDeleteDTO;
import by.it.academy.dtos.requests.productInBucket.ProductInBucketWithIdDTO;
import by.it.academy.dtos.requests.user.UserDTO;
import by.it.academy.entities.ProductInBucket;

import java.util.List;

public interface UserService<T> {

    T findUser(Long id);

    Long createUser(UserDTO request);

    Long updateUser(Long id, UserDTO dto);

    void deleteUser(Long id);

    T findUser(String login);

    List<T> findUsers();

    T findUser(UserDTO dto);

    boolean checkUser(String login);

    boolean checkUser(Long id);

    List<ProductInBucket> getProductsInBucket(Long id);

    Long addProductInBucket(ProductInBucketWithIdDTO dto);

    void deleteProductInBucket(ProductInBucketDeleteDTO dto);

    Long decreaseProductInBucket(ProductInBucketWithIdDTO dto);

    void deleteProductsInBucket(Long id);

    double getCostProductsInBucket(Long id);

    void buyProductsInBucket(Long id);

}
