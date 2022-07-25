package by.it.academy.controllers.product;

import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.entities.Product;
import by.it.academy.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for performing operations with a product.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Find product by its id.
     *
     * @return the product with the given id. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if product none found.
     */
    @GetMapping("{id}")
    public Product findProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }

    /**
     * Save a product by given ProductDTO.
     *
     * @return the id of saved product. Return HttpStatus.CREATED when request passed without exceptions.
     * Throw EntityExistsException when product with such name, category, type and producer exists.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody @Valid ProductDTO dto) {
        return productService.createProduct(dto);
    }

    /**
     * Update a product by given id and ProductDTO.
     *
     * @return the id of updated product. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if producer none found. Throw EntityExistsException when product with such name,
     * category, type and producer exists.
     */
    @PutMapping("{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return productService.updateProduct(id, dto);
    }

    /**
     * Delete a product by given id.
     *
     * Return HttpStatus.OK when request passed without exceptions. Throw NoSUchElementException if product none found.
     */
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    /**
     * Find all products.
     *
     * @return all products. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping
    public List<Product> findProducts() {
        return productService.findProducts();
    }

    /**
     * Find products by their name.
     *
     * @return products by their name. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping("name/{name}")
    public List<Product> findProducts(@PathVariable String name) {
        return productService.findProducts(name);
    }

    /**
     * Find last products by given amount.
     *
     * @return last products by given amount. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping("last/{amountLastProducts}")
    public List<Product> findProducts(@PathVariable int amountLastProducts) {
        return productService.findProducts(amountLastProducts);
    }

    /**
     * Find products by given category name.
     *
     * @return products by given category name. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping("category/{category}")
    public List<Product> findProductsByCategory(@PathVariable String category) {
        return productService.findProductsByCategoryName(category);
    }

    /**
     * Find products by given type name.
     *
     * @return products by given type name. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping(path = "type/{type}")
    public List<Product> findProductsByType(@PathVariable String type) {
        return productService.findProductsByTypeName(type);
    }

    /**
     * Find products by given producer name.
     *
     * @return products by given producer name. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping("producer/{producer}")
    public List<Product> findProductsByProducer(@PathVariable String producer) {
        return productService.findProductsByProducerName(producer);
    }

    /**
     * Find products by given search word. Find in the name of category, name of type,
     * name of producer, name of product.
     *
     * @return products by given search word. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping("search/{search}")
    public List<Product> search(@PathVariable String search) {
        return productService.searchProducts(search);
    }

    /**
     * Decrease product amount on the store by given ProductDecreaseRequest.
     *
     * @return id decreased product. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if product none found. Throw NotEnoughProductException if product amount
     * not enough on the store.
     */
    @PutMapping("decrease")
    public Long decreaseProductAmount(@RequestBody @Valid ProductDecreaseRequest request) {
        return productService.decreaseProductAmount(request);
    }

}
