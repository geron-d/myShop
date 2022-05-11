package by.it.academy.services.product;

import by.it.academy.comparators.product.ProductIdDescComparator;
import by.it.academy.contants.Order;
import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    public List<Product> getAllDesc() {
        return repository.getAllProducts(Order.DESC);
    }

    @Override
    public boolean decreaseProductAmount(Product product, int amount) {
        boolean isUpdated = false;
        if (product.getAmount() >= amount) {
            product = new Product(product.getId(), product.getCategory(), product.getType(), product.getName(),
                    product.getImage_path(), product.getLocalDate(), product.getProducer(),
                    product.getAmount() - amount, product.getPrice());
            isUpdated = update(product, product);
        }
        return isUpdated;
    }

    @Override
    public List<Product> search(String search) {
        return repository.search(search);
    }

    @Override
    public List<Product> getType(String type, Order order) {
        return repository.getProductsInType(type, order);
    }

    @Override
    public List<Product> sortByCategory(String[] categories) {
        List<Product> categoryProducts = new ArrayList<>();
        if (categories == null) {
            return null;
        }
        Arrays.stream(categories)
                .map(category -> getProductsInCategory(category, Order.ASC))
                .forEachOrdered(categoryProducts::addAll);
        return categoryProducts;
    }

    @Override
    public List<Product> sortByType(String[] types) {
        List<Product> typeProducts = new ArrayList<>();
        if (types == null) {
            return null;
        }
        Arrays.stream(types)
                .map(type -> getType(type, Order.ASC))
                .forEachOrdered(typeProducts::addAll);
        return typeProducts;
    }

    @Override
    public List<Product> sort(String[] categories, String[] types) {
        List<Product> sortProducts = new ArrayList<>();
        List<Product> categoryProducts = sortByCategory(categories);
        List<Product> typeProducts = sortByType(types);
        if (categories == null && types == null) { // todo Objects
            return null;// todo not return null
        } else if (categories != null) {
            sortProducts.addAll(categoryProducts);
        }
        if (types != null) {
            sortProducts.addAll(typeProducts);
        }
        Comparator<Product> productComparator = new ProductIdDescComparator();
        sortProducts = sortProducts.stream().distinct().sorted(productComparator).collect(Collectors.toList());
        return sortProducts;
    }
}
