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

@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService<User> userService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid UserDTO dto) {
        return userService.createUser(dto);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path = "login/{login}")
    @ResponseStatus(HttpStatus.OK)
    public User findUser(@PathVariable String login) {
        return userService.findUser(login);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @GetMapping(path = "user")
    @ResponseStatus(HttpStatus.OK)
    public User findUser(@RequestBody @Valid UserDTO dto) {
        return userService.findUser(dto);
    }

    @GetMapping(path = "exist/login/{login}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkUser(@PathVariable String login) {
        return userService.checkUser(login);
    }

    @GetMapping(path = "exist/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkUser(@PathVariable Long id) {
        return userService.checkUser(id);
    }

    @GetMapping(path = "bucket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInBucket> getProductsInBucket(@PathVariable Long id) {
        return userService.getProductsInBucket(id);
    }

    @PutMapping(path = "bucket")
    @ResponseStatus(HttpStatus.OK)
    public Long addProductInBucket(@RequestBody @Valid ProductInBucketWithIdDTO dto) {
        return userService.addProductInBucket(dto);
    }

    @DeleteMapping(path = "bucket")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductInBucket(@RequestBody @Valid ProductInBucketDeleteDTO dto) {
        userService.deleteProductInBucket(dto);
    }

    @PutMapping(path = "bucket/decrease")
    @ResponseStatus(HttpStatus.OK)
    public Long decreaseProductInBucket(@RequestBody @Valid ProductInBucketWithIdDTO dto) {
        return userService.decreaseProductInBucket(dto);
    }

    @DeleteMapping(path = "bucket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductsInBucket(@PathVariable Long id) {
        userService.deleteProductsInBucket(id);
    }

    @GetMapping(path = "bucket/cost/{id}")
    @ResponseStatus(HttpStatus.OK)
    public double getCostProductsInBucket(@PathVariable Long id) {
        return userService.getCostProductsInBucket(id);
    }

    @DeleteMapping(path = "bucket/buy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void buyProductsInBucket(@PathVariable Long id) {
        userService.buyProductsInBucket(id);
    }

}
