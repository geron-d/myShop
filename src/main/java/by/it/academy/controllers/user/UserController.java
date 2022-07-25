package by.it.academy.controllers.user;

import by.it.academy.dtos.requests.productInBucket.ProductInBucketDeleteDTO;
import by.it.academy.dtos.requests.productInBucket.ProductInBucketWithIdDTO;
import by.it.academy.dtos.requests.user.UserDTO;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for performing operations with a user.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Find user by its id.
     *
     * @return the user with the given id. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user none found.
     */
    @GetMapping("{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    /**
     * Save a user by given UserDTO.
     *
     * @return the id of saved user. Return HttpStatus.CREATED when request passed without exceptions.
     * Throw SQLIntegrityConstraintViolationException when user with such login exists.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid UserDTO dto) {
        return userService.createUser(dto);
    }

    /**
     * Update a user by given id and UserDTO.
     *
     * @return the id of updated user. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user none found. Throw SQLIntegrityConstraintViolationException
     * when user with such login exists.
     */
    @PutMapping("{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    /**
     * Delete a user by given id.
     *
     * Return HttpStatus.OK when request passed without exceptions. Throw NoSUchElementException if user not found.
     */
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Find user by its login.
     *
     * @return the user with the given login. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user none found.
     */
    @GetMapping("login/{login}")
    public User findUser(@PathVariable String login) {
        return userService.findUser(login);
    }

    /**
     * Find all users.
     *
     * @return all users. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping
    public List<User> findUsers() {
        return userService.findUsers();
    }

    /**
     * Find user by its UserDTO.
     *
     * @return the user with the given UserDTO. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user not found.
     */
    @GetMapping("user")
    public User findUser(@RequestBody @Valid UserDTO dto) {
        return userService.findUser(dto);
    }

    /**
     * Check existing of user by its login.
     *
     * @return true when such user exists and false when does not. Return HttpStatus.OK when request passed
     * without exceptions.
     */
    @GetMapping("exist/login/{login}")
    public boolean checkUser(@PathVariable String login) {
        return userService.checkUser(login);
    }

    /**
     * Check existing of user by its id.
     *
     * @return true when such user exists and false when does not. Return HttpStatus.OK when request passed
     * without exceptions.
     */
    @GetMapping("exist/id/{id}")
    public boolean checkUser(@PathVariable Long id) {
        return userService.checkUser(id);
    }

    /**
     * Get products in user bucket by user id.
     *
     * @return products in user bucket by user id. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user not found.
     */
    @GetMapping("bucket/{id}")
    public List<ProductInBucket> getProductsInBucket(@PathVariable Long id) {
        return userService.getProductsInBucket(id);
    }

    /**
     * Add product in user bucket by ProductInBucketWithIdDTO.
     *
     * @return id of added productInBucket. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user or product not found.
     */
    @PutMapping("bucket")
    public Long addProductInBucket(@RequestBody @Valid ProductInBucketWithIdDTO dto) {
        return userService.addProductInBucket(dto);
    }

    /**
     * Delete product in user bucket by ProductInBucketWithIdDTO.
     *
     * Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user or product not found.
     */
    @DeleteMapping("bucket")
    public void deleteProductInBucket(@RequestBody @Valid ProductInBucketDeleteDTO dto) {
        userService.deleteProductInBucket(dto);
    }

    /**
     * Decrease product amount in user bucket by ProductInBucketWithIdDTO.
     *
     * @return id of decreased productInBucket. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user or product not found. Throw NotEnoughProductException if product amount
     * not enough in the bucket.
     */
    @PutMapping("bucket/decrease")
    public Long decreaseProductInBucket(@RequestBody @Valid ProductInBucketWithIdDTO dto) {
        return userService.decreaseProductInBucket(dto);
    }

    /**
     * Delete all products in user bucket by user id.
     *
     * Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if user or product not found.
     */
    @DeleteMapping("bucket/{id}")
    public void deleteProductsInBucket(@PathVariable Long id) {
        userService.deleteProductsInBucket(id);
    }

    /**
     * Get cost of all products in user bucket by user id.
     *
     * @return ost of all products in user bucket by user id. Return HttpStatus.OK when request passed
     * without exceptions.
     * Throw NoSUchElementException if user not found.
     */
    @GetMapping("bucket/cost/{id}")
    public double getCostProductsInBucket(@PathVariable Long id) {
        return userService.getCostProductsInBucket(id);
    }

    /**
     * Delete all products from user bucket by user id. Decrease product amount on the store.
     *
     * Return HttpStatus.OK when request passed without exceptions. Throw NoSUchElementException if user not found.
     * Throw NotEnoughProductException if product amount not enough on the store.
     */
    @DeleteMapping("bucket/buy/{id}")
    public void buyProductsInBucket(@PathVariable Long id) {
        userService.buyProductsInBucket(id);
    }

}
