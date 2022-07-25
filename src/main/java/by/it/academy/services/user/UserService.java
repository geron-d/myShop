package by.it.academy.services.user;

import by.it.academy.dtos.requests.productInBucket.ProductInBucketDeleteDTO;
import by.it.academy.dtos.requests.productInBucket.ProductInBucketWithIdDTO;
import by.it.academy.dtos.requests.user.UserDTO;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;

import java.util.List;

/**
 * Interface for generic operations on a service for a user.
 *
 * @author Maxim Zhevnov
 */
public interface UserService {

    /**
     * Find user by its id.
     *
     * @return the user with the given id. Throw NoSUchElementException if user none found.
     */
    User findUser(Long id);

    /**
     * Save a user by given UserDTO.
     *
     * @return the id of saved user. Throw SQLIntegrityConstraintViolationException when user with such login exists.
     */
    Long createUser(UserDTO request);

    /**
     * Update a user by given id and UserDTO.
     *
     * @return the id of updated user. Throw NoSUchElementException if user none found.
     * Throw SQLIntegrityConstraintViolationException when user with such login exists.
     */
    Long updateUser(Long id, UserDTO dto);

    /**
     * Delete a user by given id.
     *
     * Throw NoSUchElementException if user not found.
     */
    void deleteUser(Long id);

    /**
     * Find user by its login.
     *
     * @return the user with the given login. Throw NoSUchElementException if user none found.
     */
    User findUser(String login);

    /**
     * Find all users.
     *
     * @return all users.
     */
    List<User> findUsers();

    /**
     * Find user by its UserDTO.
     *
     * @return the user with the given UserDTO. Throw NoSUchElementException if user not found.
     */
    User findUser(UserDTO dto);

    /**
     * Check existing of user by its login.
     *
     * @return true when such user exists and false when does not.
     */
    boolean checkUser(String login);

    /**
     * Check existing of user by its id.
     *
     * @return true when such user exists and false when does not.
     */
    boolean checkUser(Long id);

    /**
     * Get products in user bucket by user id.
     *
     * @return products in user bucket by user id. Throw NoSUchElementException if user not found.
     */
    List<ProductInBucket> getProductsInBucket(Long id);

    /**
     * Add product in user bucket by ProductInBucketWithIdDTO.
     *
     * @return id of added productInBucket. Throw NoSUchElementException if user or product not found.
     */
    Long addProductInBucket(ProductInBucketWithIdDTO dto);

    /**
     * Delete product in user bucket by ProductInBucketWithIdDTO.
     *
     * Throw NoSUchElementException if user or product not found.
     */
    void deleteProductInBucket(ProductInBucketDeleteDTO dto);

    /**
     * Decrease product amount in user bucket by ProductInBucketWithIdDTO.
     *
     * @return id of decreased productInBucket. Throw NoSUchElementException if user or product not found.
     * Throw NotEnoughProductException if product amount not enough in the bucket.
     */
    Long decreaseProductInBucket(ProductInBucketWithIdDTO dto);

    /**
     * Delete all products in user bucket by user id.
     *
     * Throw NoSUchElementException if user or product not found.
     */
    void deleteProductsInBucket(Long id);

    /**
     * Get cost of all products in user bucket by user id.
     *
     * @return ost of all products in user bucket by user id. Throw NoSUchElementException if user not found.
     */
    double getCostProductsInBucket(Long id);

    /**
     * Delete all products from user bucket by user id. Decrease product amount on the store.
     *
     * Throw NoSUchElementException if user not found. Throw NotEnoughProductException if product amount not enough
     * on the store.
     */
    void buyProductsInBucket(Long id);

}
