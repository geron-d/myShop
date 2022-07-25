package by.it.academy.services.product;

import by.it.academy.dtos.requests.product.ProductDTO;
import by.it.academy.dtos.requests.product.ProductDecreaseRequest;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.category.CategoryAPIService;
import by.it.academy.services.producer.ProducerAPIService;
import by.it.academy.services.type.TypeAPIService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing product service")
public class ProductAPIServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryAPIService categoryAPIService;

    @Mock
    private TypeAPIService typeAPIService;

    @Mock
    private ProducerAPIService producerAPIService;

    @InjectMocks
    private ProductAPIService productAPIService;

    Product product;
    Category category;
    Type type;
    Producer producer;

    public Category getCategory() {
        return Category.builder()
                .id(1L)
                .name("headphones")
                .build();
    }

    public Type getType() {
        return Type.builder()
                .id(1L)
                .name("wireless")
                .build();
    }

    public Producer getProducer() {
        return Producer.builder()
                .id(1L)
                .name("F9")
                .build();
    }

    @BeforeEach
    public void setProduct() {
        category = getCategory();
        type = getType();
        producer = getProducer();
        product = Product.builder()
                .id(1L)
                .category(category)
                .type(type)
                .producer(producer)
                .dateInserting(LocalDate.of(2022, 4, 16))
                .imagePath("/images/1.jpg")
                .price(34.9)
                .amount(100)
                .name("F9")
                .build();
    }

    public Product getSavingProduct() {
        return Product.builder()
                .category(product.getCategory())
                .type(product.getType())
                .producer(product.getProducer())
                .dateInserting(product.getDateInserting())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .amount(product.getAmount())
                .name(product.getName())
                .build();
    }

    public ProductDTO getProductDTO() {
        return ProductDTO.builder()
                .category(product.getCategory().getName())
                .type(product.getType().getName())
                .producer(product.getProducer().getName())
                .dateInserting(product.getDateInserting())
                .imagePath(product.getImagePath())
                .price(product.getPrice())
                .amount(product.getAmount())
                .name(product.getName())
                .build();
    }

    public Product getAnotherProduct() {
        Type anotherType = Type.builder()
                .id(2L)
                .name("wireless overhead")
                .build();
        Producer anotherProducer = Producer.builder()
                .id(2L)
                .name("Defender")
                .build();
        return Product.builder()
                .id(1L)
                .category(category)
                .type(anotherType)
                .producer(anotherProducer)
                .dateInserting(LocalDate.of(2022, 4, 16))
                .imagePath("/images/2.jpg")
                .price(34.83)
                .amount(100)
                .name("Defender FreeMotion")
                .build();
    }

    public ProductDecreaseRequest getProductDecreasedRequest() {
        return ProductDecreaseRequest.builder()
                .productId(product.getId())
                .amount(2)
                .build();
    }

    @Test
    @DisplayName("JUnit test for findProduct method with arg id when product exists " +
            "for returning product is not null")
    void checkFindProductWithArgIdWhenProductExistsReturnsNotNull() {
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
        Assertions.assertNotNull(productAPIService.findProduct(product.getId()));
        Mockito.verify(productRepository, Mockito.times(1)).findById(product.getId());
    }

    @Test
    @DisplayName("JUnit test for findProduct method with arg id when product exists")
    void checkFindProductWithArgIdWhenProductExists() {
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
        Assertions.assertEquals(product, productAPIService.findProduct(product.getId()));
        Mockito.verify(productRepository, Mockito.times(1)).findById(product.getId());
    }


    @Test
    @DisplayName("JUnit test for findProduct method with arg id when product does not exist")
    void checkFindProductWithArgIdWhenProductDoesNotExist() {
        Mockito.when(productRepository.findById(product.getId())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> productAPIService.findProduct(product.getId()));
        Mockito.verify(productRepository, Mockito.times(1)).findById(product.getId());
    }

    @Test
    @DisplayName("JUnit test for createProduct method with arg ProductDTO for returning product is not null")
    void checkCreateProductWithArgDTOReturnsNotNull() {
        Product savingProduct = getSavingProduct();
        ProductDTO dto = getProductDTO();
        Mockito.when(categoryAPIService.findCategory(dto.getCategory())).thenReturn(product.getCategory());
        Mockito.when(typeAPIService.findType(dto.getType())).thenReturn(product.getType());
        Mockito.when(producerAPIService.findProducer(dto.getProducer())).thenReturn(product.getProducer());
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(savingProduct)).thenReturn(product);
        Assertions.assertNotNull(productAPIService.createProduct(dto));
        Mockito.verify(categoryAPIService, Mockito.times(2)).findCategory(dto.getCategory());
        Mockito.verify(typeAPIService, Mockito.times(2)).findType(dto.getType());
        Mockito.verify(producerAPIService, Mockito.times(2)).findProducer(dto.getProducer());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(savingProduct);
    }

    @Test
    @DisplayName("JUnit test for createProduct method with arg ProductDTO")
    void checkCreateProductWithArgDTO() {
        Product savingProduct = getSavingProduct();
        ProductDTO dto = getProductDTO();
        Mockito.when(categoryAPIService.findCategory(dto.getCategory())).thenReturn(product.getCategory());
        Mockito.when(typeAPIService.findType(dto.getType())).thenReturn(product.getType());
        Mockito.when(producerAPIService.findProducer(dto.getProducer())).thenReturn(product.getProducer());
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(savingProduct)).thenReturn(product);
        Assertions.assertEquals(product.getId(), productAPIService.createProduct(dto));
        Mockito.verify(categoryAPIService, Mockito.times(2)).findCategory(dto.getCategory());
        Mockito.verify(typeAPIService, Mockito.times(2)).findType(dto.getType());
        Mockito.verify(producerAPIService, Mockito.times(2)).findProducer(dto.getProducer());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(savingProduct);
    }

    @Test
    @DisplayName("JUnit test for createProduct method with arg ProductDTO " +
            "when product with such dto already exists")
    void checkCreateProductWithArgDTOWhenProductWithSuchDTOAlreadyExists() {
        ProductDTO dto = getProductDTO();
        Mockito.when(categoryAPIService.findCategory(dto.getCategory())).thenReturn(product.getCategory());
        Mockito.when(typeAPIService.findType(dto.getType())).thenReturn(product.getType());
        Mockito.when(producerAPIService.findProducer(dto.getProducer())).thenReturn(product.getProducer());
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(true);
        Assertions.assertThrows(EntityExistsException.class, () ->
                productAPIService.createProduct(dto));
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(dto.getCategory());
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(dto.getType());
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(dto.getProducer());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with args id and ProductDTO when product with such id exists " +
            "for returning product is not null")
    void checkUpdateProductWithArgsIdAndProductDTOWhenProductWithSuchIdExistsReturnsNotNull() {
        ProductDTO dto = getProductDTO();
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(true);
        Mockito.when(categoryAPIService.findCategory(dto.getCategory())).thenReturn(product.getCategory());
        Mockito.when(typeAPIService.findType(dto.getType())).thenReturn(product.getType());
        Mockito.when(producerAPIService.findProducer(dto.getProducer())).thenReturn(product.getProducer());
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Assertions.assertNotNull(productAPIService.updateProduct(product.getId(), dto));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
        Mockito.verify(categoryAPIService, Mockito.times(2)).findCategory(dto.getCategory());
        Mockito.verify(typeAPIService, Mockito.times(2)).findType(dto.getType());
        Mockito.verify(producerAPIService, Mockito.times(2)).findProducer(dto.getProducer());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with args id and ProductDTO when product with such id exists")
    void checkUpdateProductWithArgsIdAndProductDTOWhenProductWithSuchIdExists() {
        ProductDTO dto = getProductDTO();
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(true);
        Mockito.when(categoryAPIService.findCategory(dto.getCategory())).thenReturn(product.getCategory());
        Mockito.when(typeAPIService.findType(dto.getType())).thenReturn(product.getType());
        Mockito.when(producerAPIService.findProducer(dto.getProducer())).thenReturn(product.getProducer());
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Assertions.assertEquals(product.getId(), productAPIService.updateProduct(product.getId(), dto));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
        Mockito.verify(categoryAPIService, Mockito.times(2)).findCategory(dto.getCategory());
        Mockito.verify(typeAPIService, Mockito.times(2)).findType(dto.getType());
        Mockito.verify(producerAPIService, Mockito.times(2)).findProducer(dto.getProducer());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with args id and ProductDTO " +
            "when product with such id exists and product with such name already exists")
    void checkUpdateProductWithArgsIdAndProductDTOWhenProductWithSuchIdExistsAndProductWithSuchNameAlreadyExists() {
        ProductDTO dto = getProductDTO();
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(true);
        Mockito.when(categoryAPIService.findCategory(dto.getCategory())).thenReturn(product.getCategory());
        Mockito.when(typeAPIService.findType(dto.getType())).thenReturn(product.getType());
        Mockito.when(producerAPIService.findProducer(dto.getProducer())).thenReturn(product.getProducer());
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(true);
        Assertions.assertThrows(EntityExistsException.class, () ->
                productAPIService.updateProduct(product.getId(), dto));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(dto.getCategory());
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(dto.getType());
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(dto.getProducer());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with args id and ProductDTO " +
            "when product with such id does not exists")
    void checkUpdateProductWithArgsIdAndProductDTOWhenProductWithSuchIdDoesNotExists() {
        ProductDTO dto = getProductDTO();
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(false);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productAPIService.updateProduct(product.getId(), dto));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with arg Product when product with such id exists " +
            "for returning product is not null")
    void checkUpdateProductWithArgProductWhenProductWithSuchIdExistsReturnsNotNull() {
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(true);
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Assertions.assertNotNull(productAPIService.updateProduct(product));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with arg Product when product with such id exists")
    void checkUpdateProductWithArgProductWhenProductWithSuchIdExists() {
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(true);
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Assertions.assertEquals(product.getId(), productAPIService.updateProduct(product));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with arg Product " +
            "when product with such id exists and product with such name already exists")
    void checkUpdateProductWithArgProductWhenProductWithSuchIdExistsAndProductWithSuchNameAlreadyExists() {
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(true);
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(true);
        Assertions.assertThrows(EntityExistsException.class, () ->
                productAPIService.updateProduct(product));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
    }

    @Test
    @DisplayName("JUnit test for updateProduct method with arg Product when product with such id does not exists")
    void checkUpdateProductWithArgProductWhenProductWithSuchIdDoesNotExists() {
        Mockito.when(productRepository.existsById(product.getId())).thenReturn(false);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productAPIService.updateProduct(product));
        Mockito.verify(productRepository, Mockito.times(1)).existsById(product.getId());
    }

    @Test
    @DisplayName("JUnit test for deleteProduct method with arg id when product with such id exists")
    void checkDeleteProductWithArgIdWhenProductWithSuchIdExists() {
        Mockito.doNothing().when(productRepository).deleteById(product.getId());
        productAPIService.deleteProduct(product.getId());
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(product.getId());
    }

    @Test
    @DisplayName("JUnit test for findProducts method without args for returning not null")
    void checkFindProductsWithoutArgsReturnsNotNull() {
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Assertions.assertNotNull(productAPIService.findProducts());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findProducts method without args for returning empty")
    void checkFindProductsWithoutArgsReturnsEmpty() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of());
        Assertions.assertEquals(List.of(), productAPIService.findProducts());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findProducts method without args when product exist")
    void checkFindProductsWithoutArgsWhenProductsExist() {
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(products, productAPIService.findProducts());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findProducts method without args when product exist for size")
    void checkFindProductsWithoutArgsWhenProductsExistForSize() {
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(products.size(), productAPIService.findProducts().size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findProducts method without args when product exist for first product")
    void checkFindProductsWithoutArgsWhenProductsExistForFirstProduct() {
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(products.get(0), productAPIService.findProducts().get(0));
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg String name for returning not null")
    void checkFindProductsWithArgStringNameReturnsNotNull() {
        String name = product.getName();
        List<Product> products = List.of(product);
        Mockito.when(productRepository.findAllByName(name)).thenReturn(products);
        Assertions.assertNotNull(productAPIService.findProducts(name));
        Mockito.verify(productRepository, Mockito.times(1)).findAllByName(name);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg String name for returning empty")
    void checkFindProductsWithArgStringNameReturnsEmpty() {
        String name = "some name";
        Mockito.when(productRepository.findAllByName(name)).thenReturn(List.of());
        Assertions.assertEquals(List.of(), productAPIService.findProducts(name));
        Mockito.verify(productRepository, Mockito.times(1)).findAllByName(name);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg String name when product exist")
    void checkFindProductsWithArgStringNameWhenProductsExist() {
        String name = product.getName();
        List<Product> products = List.of(product);
        Mockito.when(productRepository.findAllByName(name)).thenReturn(products);
        Assertions.assertEquals(products, productAPIService.findProducts(name));
        Mockito.verify(productRepository, Mockito.times(1)).findAllByName(name);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg String name when product exist for size")
    void checkFindProductsWithArgStringNameWhenProductsExistForSize() {
        String name = product.getName();
        List<Product> products = List.of(product);
        Mockito.when(productRepository.findAllByName(name)).thenReturn(products);
        Assertions.assertEquals(products.size(), productAPIService.findProducts(name).size());
        Mockito.verify(productRepository, Mockito.times(1)).findAllByName(name);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg String name when product exist for first product")
    void checkFindProductsWithArgStringNameWhenProductsExistForFirstProduct() {
        String name = product.getName();
        List<Product> products = List.of(product);
        Mockito.when(productRepository.findAllByName(name)).thenReturn(products);
        Assertions.assertEquals(products.get(0), productAPIService.findProducts(name).get(0));
        Mockito.verify(productRepository, Mockito.times(1)).findAllByName(name);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg int amountLastProducts for returning not null")
    void checkFindProductsWithArgIntAmountLastProductsReturnsNotNull() {
        int amountLastProducts = 2;
        Pageable lastProducts = PageRequest.of(0, amountLastProducts, Sort.by("id").descending());
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Page<Product> productsPage = new PageImpl<>(products);
        Mockito.when(productRepository.findAll(lastProducts)).thenReturn(productsPage);
        Assertions.assertNotNull(productAPIService.findProducts(amountLastProducts));
        Mockito.verify(productRepository, Mockito.times(1)).findAll(lastProducts);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg int amountLastProducts name when product exist")
    void checkFindProductsWithArgIntAmountLastProductsWhenProductsExist() {
        int amountLastProducts = 2;
        Pageable lastProducts = PageRequest.of(0, amountLastProducts, Sort.by("id").descending());
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Page<Product> productsPage = new PageImpl<>(products);
        Mockito.when(productRepository.findAll(lastProducts)).thenReturn(productsPage);
        Assertions.assertEquals(productsPage.getContent(), productAPIService.findProducts(amountLastProducts));
        Mockito.verify(productRepository, Mockito.times(1)).findAll(lastProducts);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg int amountLastProducts when product exist for size")
    void checkFindProductsWithArgIntAmountLastProductsWhenProductsExistForSize() {
        int amountLastProducts = 2;
        Pageable lastProducts = PageRequest.of(0, amountLastProducts, Sort.by("id").descending());
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Page<Product> productsPage = new PageImpl<>(products);
        Mockito.when(productRepository.findAll(lastProducts)).thenReturn(productsPage);
        Assertions.assertEquals(productsPage.getContent().size(),
                productAPIService.findProducts(amountLastProducts).size());
        Mockito.verify(productRepository, Mockito.times(1)).findAll(lastProducts);
    }

    @Test
    @DisplayName("JUnit test for findProducts method with arg int amountLastProducts " +
            "when product exist for first product")
    void checkFindProductsWithArgIntAmountLastProductsWhenProductsExistForFirstProduct() {
        int amountLastProducts = 2;
        Pageable lastProducts = PageRequest.of(0, amountLastProducts, Sort.by("id").descending());
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Page<Product> productsPage = new PageImpl<>(products);
        Mockito.when(productRepository.findAll(lastProducts)).thenReturn(productsPage);
        Assertions.assertEquals(productsPage.getContent().get(productsPage.getSize() - 1),
                productAPIService.findProducts(amountLastProducts).get(products.size() - 1));
        Mockito.verify(productRepository, Mockito.times(1)).findAll(lastProducts);
    }

    @Test
    @DisplayName("JUnit test for findProductsByCategoryName method with arg String categoryName for returning not null")
    void checkFindProductsByCategoryNameWithArgStringCategoryNameReturnsNotNull() {
        String categoryName = product.getCategory().getName();
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.findCategory(categoryName)).thenReturn(category);
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertNotNull(productAPIService.findProductsByCategoryName(categoryName));
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(categoryName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for findProductsByCategoryName method with arg String categoryName " +
            "for returning NoSuchElementException")
    void checkFindProductsByCategoryNameWithArgStringCategoryNameReturnsNoSuchElementException() {
        String categoryName = "some category";
        Mockito.when(categoryAPIService.findCategory(categoryName)).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productAPIService.findProductsByCategoryName(categoryName));
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(categoryName);
    }

    @Test
    @DisplayName("JUnit test for findProductsByCategoryName method with arg String categoryName when product exist")
    void checkFindProductsByCategoryNameWithArgStringCategoryNameWhenProductsExist() {
        String categoryName = product.getCategory().getName();
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.findCategory(categoryName)).thenReturn(category);
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertEquals(products, productAPIService.findProductsByCategoryName(categoryName));
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(categoryName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for findProductsByCategoryName method with arg String categoryName " +
            "when product exist for size")
    void checkFindProductsByCategoryNameWithArgStringCategoryNameWhenProductsExistForSize() {
        String categoryName = product.getCategory().getName();
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.findCategory(categoryName)).thenReturn(category);
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertEquals(products.size(), productAPIService.findProductsByCategoryName(categoryName).size());
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(categoryName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for findProductsByCategoryName method with arg String categoryName " +
            "when product exist for first product")
    void checkFindProductsByCategoryNameWithArgStringCategoryNameWhenProductsExistForFirstProduct() {
        String categoryName = product.getCategory().getName();
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.findCategory(categoryName)).thenReturn(category);
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertEquals(products.get(0), productAPIService.findProductsByCategoryName(categoryName).get(0));
        Mockito.verify(categoryAPIService, Mockito.times(1)).findCategory(categoryName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for findProductsByTypeName method with arg String typeName for returning not null")
    void checkFindProductsByTypeNameWithArgStringTypeNameReturnsNotNull() {
        String typeName = product.getType().getName();
        List<Product> products = List.of(product);
        Mockito.when(typeAPIService.findType(typeName)).thenReturn(type);
        Mockito.when(productRepository.findAllByType(type)).thenReturn(products);
        Assertions.assertNotNull(productAPIService.findProductsByTypeName(typeName));
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(typeName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByType(type);
    }

    @Test
    @DisplayName("JUnit test for findProductsByTypeName method with arg String typeName " +
            "for returning NoSuchElementException")
    void checkFindProductsByTypeNameWithArgStringTypeNameReturnsNoSuchElementException() {
        String typeName = "some type";
        Mockito.when(typeAPIService.findType(typeName)).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productAPIService.findProductsByTypeName(typeName));
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(typeName);
    }

    @Test
    @DisplayName("JUnit test for findProductsByTypeName method with arg String typeName when product exist")
    void checkFindProductsByTypeNameWithArgStringTypeNameWhenProductsExist() {
        String typeName = product.getType().getName();
        List<Product> products = List.of(product);
        Mockito.when(typeAPIService.findType(typeName)).thenReturn(type);
        Mockito.when(productRepository.findAllByType(type)).thenReturn(products);
        Assertions.assertEquals(products, productAPIService.findProductsByTypeName(typeName));
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(typeName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByType(type);
    }

    @Test
    @DisplayName("JUnit test for findProductsByTypeName method with arg String typeName " +
            "when product exist for size")
    void checkFindProductsByTypeNameWithArgStringTypeNameWhenProductsExistForSize() {
        String typeName = product.getType().getName();
        List<Product> products = List.of(product);
        Mockito.when(typeAPIService.findType(typeName)).thenReturn(type);
        Mockito.when(productRepository.findAllByType(type)).thenReturn(products);
        Assertions.assertEquals(products.size(), productAPIService.findProductsByTypeName(typeName).size());
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(typeName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByType(type);
    }

    @Test
    @DisplayName("JUnit test for findProductsByTypeName method with arg String typeName " +
            "when product exist for first product")
    void checkFindProductsByTypeNameWithArgStringTypeNameWhenProductsExistForFirstProduct() {
        String typeName = product.getType().getName();
        List<Product> products = List.of(product);
        Mockito.when(typeAPIService.findType(typeName)).thenReturn(type);
        Mockito.when(productRepository.findAllByType(type)).thenReturn(products);
        Assertions.assertEquals(products.get(0), productAPIService.findProductsByTypeName(typeName).get(0));
        Mockito.verify(typeAPIService, Mockito.times(1)).findType(typeName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByType(type);
    }

    @Test
    @DisplayName("JUnit test for findProductsByProducerName method with arg String producerName for returning not null")
    void checkFindProductsByProducerNameWithArgStringProducerNameReturnsNotNull() {
        String producerName = product.getProducer().getName();
        List<Product> products = List.of(product);
        Mockito.when(producerAPIService.findProducer(producerName)).thenReturn(producer);
        Mockito.when(productRepository.findAllByProducer(producer)).thenReturn(products);
        Assertions.assertNotNull(productAPIService.findProductsByProducerName(producerName));
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(producerName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByProducer(producer);
    }

    @Test
    @DisplayName("JUnit test for findProductsByProducerName method with arg String producerName " +
            "for returning NoSuchElementException")
    void checkFindProductsByProducerNameWithArgStringProducerNameReturnsNoSuchElementException() {
        String producerName = "some producer";
        Mockito.when(producerAPIService.findProducer(producerName)).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productAPIService.findProductsByProducerName(producerName));
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(producerName);
    }

    @Test
    @DisplayName("JUnit test for findProductsByProducerName method with arg String producerName when product exist")
    void checkFindProductsByProducerNameWithArgStringProducerNameWhenProductsExist() {
        String producerName = product.getProducer().getName();
        List<Product> products = List.of(product);
        Mockito.when(producerAPIService.findProducer(producerName)).thenReturn(producer);
        Mockito.when(productRepository.findAllByProducer(producer)).thenReturn(products);
        Assertions.assertEquals(products, productAPIService.findProductsByProducerName(producerName));
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(producerName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByProducer(producer);
    }

    @Test
    @DisplayName("JUnit test for findProductsByProducerName method with arg String producerName " +
            "when product exist for size")
    void checkFindProductsByProducerNameWithArgStringProducerNameWhenProductsExistForSize() {
        String producerName = product.getProducer().getName();
        List<Product> products = List.of(product);
        Mockito.when(producerAPIService.findProducer(producerName)).thenReturn(producer);
        Mockito.when(productRepository.findAllByProducer(producer)).thenReturn(products);
        Assertions.assertEquals(products.size(), productAPIService.findProductsByProducerName(producerName).size());
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(producerName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByProducer(producer);
    }

    @Test
    @DisplayName("JUnit test for findProductsByProducerName method with arg String producerName " +
            "when product exist for first product")
    void checkFindProductsByProducerNameWithArgStringProducerNameWhenProductsExistForFirstProduct() {
        String producerName = product.getProducer().getName();
        List<Product> products = List.of(product);
        Mockito.when(producerAPIService.findProducer(producerName)).thenReturn(producer);
        Mockito.when(productRepository.findAllByProducer(producer)).thenReturn(products);
        Assertions.assertEquals(products.get(0), productAPIService.findProductsByProducerName(producerName).get(0));
        Mockito.verify(producerAPIService, Mockito.times(1)).findProducer(producerName);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByProducer(producer);
    }

    @Test
    @DisplayName("JUnit test for searchProducts method with arg String search for returning not null")
    void checkSearchProductsWithArgStringSearchReturnsNotNull() {
        String search = "headphones";
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.searchCategories(search)).thenReturn(List.of(category));
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertNotNull(productAPIService.searchProducts(search));
        Mockito.verify(categoryAPIService, Mockito.times(1)).searchCategories(search);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for searchProducts method with arg String search " +
            "for returning NoSuchElementException")
    void checkSearchProductsWithArgStringSearchReturnsEmpty() {
        String search = "some search word";
        Assertions.assertEquals(List.of(), productAPIService.searchProducts(search));
    }

    @Test
    @DisplayName("JUnit test for searchProducts method with arg String search when product exist")
    void checkSearchProductsWithArgStringSearchWhenProductsExist() {
        String search = "headphones";
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.searchCategories(search)).thenReturn(List.of(category));
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertEquals(products, productAPIService.searchProducts(search));
        Mockito.verify(categoryAPIService, Mockito.times(1)).searchCategories(search);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for searchProducts method with arg String search when product exist for size")
    void checkSearchProductsWithArgStringSearchWhenProductsExistForSize() {
        String search = "headphones";
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.searchCategories(search)).thenReturn(List.of(category));
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertEquals(products.size(), productAPIService.searchProducts(search).size());
        Mockito.verify(categoryAPIService, Mockito.times(1)).searchCategories(search);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for searchProducts method with arg String search when product exist for first product")
    void checkSearchProductsWithArgStringSearchWhenProductsExistForFirstProduct() {
        String search = "headphones";
        Product anotherProduct = getAnotherProduct();
        List<Product> products = List.of(product, anotherProduct);
        Mockito.when(categoryAPIService.searchCategories(search)).thenReturn(List.of(category));
        Mockito.when(productRepository.findAllByCategory(category)).thenReturn(products);
        Assertions.assertEquals(products.get(0), productAPIService.searchProducts(search).get(0));
        Mockito.verify(categoryAPIService, Mockito.times(1)).searchCategories(search);
        Mockito.verify(productRepository, Mockito.times(1)).findAllByCategory(category);
    }

    @Test
    @DisplayName("JUnit test for decreaseProductAmount method with arg ProductDecreaseRequest " +
            "when product with such id exists for returning product is not null")
    void checkDecreaseProductAmountWithArgProductDecreaseRequestWhenProductWithSuchIdExistsReturnsNotNull() {
        ProductDecreaseRequest request = getProductDecreasedRequest();
        Mockito.when(productRepository.findById(request.getProductId())).thenReturn(Optional.ofNullable(product));
        Mockito.when(productRepository.existsById(request.getProductId())).thenReturn(true);
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Assertions.assertNotNull(productAPIService.decreaseProductAmount(request));
        Mockito.verify(productRepository, Mockito.times(1)).findById(request.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).existsById(request.getProductId());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("JUnit test for decreaseProductAmount method with arg ProductDecreaseRequest " +
            "when product with such id exists")
    void checkDecreaseProductAmountWithArgProductDecreaseRequestWhenProductWithSuchIdExists() {
        ProductDecreaseRequest request = getProductDecreasedRequest();
        Mockito.when(productRepository.findById(request.getProductId())).thenReturn(Optional.ofNullable(product));
        Mockito.when(productRepository.existsById(request.getProductId())).thenReturn(true);
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(false);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Assertions.assertEquals(product.getId(), productAPIService.decreaseProductAmount(request));
        Mockito.verify(productRepository, Mockito.times(1)).findById(request.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).existsById(request.getProductId());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    @DisplayName("JUnit test for decreaseProductAmount method with arg ProductDecreaseRequest " +
            "when product with such id exists and product with such name already exists")
    void checkDecreaseProductAmountWithArgProductDecreaseRequestWhenProductWithSuchIdExistsAndProductWithSuchNameAlreadyExists() {
        ProductDecreaseRequest request = getProductDecreasedRequest();
        Mockito.when(productRepository.findById(request.getProductId())).thenReturn(Optional.ofNullable(product));
        Mockito.when(productRepository.existsById(request.getProductId())).thenReturn(true);
        Mockito.when(productRepository.existsByCategoryAndProducerAndTypeAndName(product.getCategory(),
                        product.getProducer(), product.getType(), product.getName()))
                .thenReturn(true);
        Assertions.assertThrows(EntityExistsException.class, () ->
                productAPIService.decreaseProductAmount(request));
        Mockito.verify(productRepository, Mockito.times(1)).findById(request.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).existsById(request.getProductId());
        Mockito.verify(productRepository, Mockito.times(1))
                .existsByCategoryAndProducerAndTypeAndName(product.getCategory(),product.getProducer(),
                        product.getType(), product.getName());
    }

    @Test
    @DisplayName("JUnit test for decreaseProductAmount method with arg ProductDecreaseRequest " +
            "when product with such id does not exists")
    void checkDecreaseProductAmountWithArgProductDecreaseRequestWhenProductWithSuchIdDoesNotExists() {
        ProductDecreaseRequest request = getProductDecreasedRequest();
        Mockito.when(productRepository.findById(request.getProductId())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productAPIService.decreaseProductAmount(request));
        Mockito.verify(productRepository, Mockito.times(1)).findById(request.getProductId());
    }
}
