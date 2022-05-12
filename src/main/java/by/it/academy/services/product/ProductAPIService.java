package by.it.academy.services.product;

import by.it.academy.comparators.product.ProductIdDescComparator;
import by.it.academy.contants.Order;
import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ProductAPIService implements ProductService<Product> {
    private final ProductRepository<Product> repository;

    public ProductAPIService(ProductRepository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(Product product) {
        return repository.create(product);
    }

    @Override
    public Product get(Product product) {
        return repository.get(product);
    }

    @Override
    public boolean update(Product product, Product newProduct) {
        return repository.update(product, newProduct);
    }

    @Override
    public boolean delete(Product product) {
        return repository.delete(product);
    }

    @Override
    public List<Product> getAllProducts(Order order) {
        return repository.getAllProducts(order);
    }

    @Override
    public Product getByID(int id) {
        return repository.getByID(id);
    }

    @Override
    public List<Product> getLastProducts(int amount, Order order) {
        return repository.getLastProducts(amount, order);
    }

    @Override
    public List<Product> getProductsInCategory(String category, Order order) {
        return repository.getProductsInCategory(category, order);
    }

    @Override
    public boolean checkProductAmount(Product product) {
        return product.getAmount() > 0;
    }

    @Override
    public boolean decreaseProductAmount(Product product, int amount) {
        boolean isProductDecreased = false;
        if (product.getAmount() >= amount) {
            product = Product.builder()
                    .id(product.getId())
                    .category(product.getCategory())
                    .type(product.getType())
                    .name(product.getName())
                    .amount(product.getAmount() - amount)
                    .price(product.getPrice())
                    .build();
            isProductDecreased = update(product, product);
        }
        return isProductDecreased;
    }

    @Override
    public List<Product> search(String search) {
        return repository.search(search);
    }

    @Override
    public List<Product> getProductsInType(String type, Order order) {
        return repository.getProductsInType(type, order);
    }

    @Override
    public List<Product> sortByCategory(String[] categories) {
        List<Product> categoryProducts = new ArrayList<>();
        if (Objects.isNull(categories)) {
            return categoryProducts;
        }
        Arrays.stream(categories)
                .map(category -> getProductsInCategory(category, Order.ASC))
                .forEachOrdered(categoryProducts::addAll);
        return categoryProducts;
    }

    @Override
    public List<Product> sortByType(String[] types) {
        List<Product> typeProducts = new ArrayList<>();
        if (Objects.isNull(types)) {
            return typeProducts;
        }
        Arrays.stream(types)
                .map(type -> getProductsInType(type, Order.ASC))
                .forEachOrdered(typeProducts::addAll);
        return typeProducts;
    }

    @Override
    public List<Product> sort(String[] categories, String[] types) {
        List<Product> sortProducts = new ArrayList<>();
        List<Product> categoryProducts = sortByCategory(categories);
        List<Product> typeProducts = sortByType(types);
        if (Objects.isNull(categories) && Objects.isNull(types)) {
            return sortProducts;
        } else if (Objects.nonNull(categories)) {
            sortProducts.addAll(categoryProducts);
        }
        if (Objects.nonNull(types)) {
            sortProducts.addAll(typeProducts);
        }
        Comparator<Product> productComparator = new ProductIdDescComparator();
        sortProducts = sortProducts.stream().distinct().sorted(productComparator).collect(Collectors.toList());
        return sortProducts;
    }
}
