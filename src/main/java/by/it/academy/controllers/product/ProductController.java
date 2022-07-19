package by.it.academy.controllers.product;

import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.dtos.requests.product.SortProductRequest;
import by.it.academy.entities.Product;
import by.it.academy.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService<Product> productService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(@RequestBody @Valid ProductDTO dto) {
        return productService.createProduct(dto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProducts() {
        return productService.findProducts();
    }

    @GetMapping(path = "name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProducts(@PathVariable String name) {
        return productService.findProducts(name);
    }

    @GetMapping(path = "last/{amountLastProducts}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProducts(@PathVariable int amountLastProducts) {
        return productService.findProducts(amountLastProducts);
    }

    @GetMapping(path = "category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByCategory(@PathVariable String category) {
        return productService.findProductsByCategoryName(category);
    }

    @GetMapping(path = "type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByType(@PathVariable String type) {
        return productService.findProductsByTypeName(type);
    }

    @GetMapping(path = "producer/{producer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByProducer(@PathVariable String producer) {
        return productService.findProductsByProducerName(producer);
    }

    @GetMapping(path = "search/{search}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> search(@PathVariable String search) {
        return productService.searchProducts(search);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "decrease")
    @ResponseStatus(HttpStatus.OK)
    public Long decreaseProductAmount(@RequestBody @Valid ProductDecreaseRequest request) {
        return productService.decreaseProductAmount(request);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "sort")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> sort(@RequestBody @Valid SortProductRequest request) {
        return productService.sortProducts(request);
    }

}
