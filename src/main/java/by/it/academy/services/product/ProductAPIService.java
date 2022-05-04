package by.it.academy.services.product;

import by.it.academy.comparators.product.ProductIdDescComparator;
import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.ArrayList;
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
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public Product getByID(int id) {
        return repository.getByID(id);
    }

    @Override
    public List<Product> getLastProducts(int amount) {
        return repository.getLastProducts(amount);
    }

    @Override
    public List<Product> getCategoryDesc(String category) {
        return repository.getCategoryDesc(category);
    }

    @Override
    public boolean checkProductAmount(Product product) {
        return product.getAmount() > 0;
    }

    @Override
    public List<Product> getAllDesc() {
        return repository.getAllDesc();
    }

    @Override
    public boolean decreaseProductAmount(Product product, int amount) {
        boolean isDecreased = true;
        if (product.getAmount() >= amount) {
            product.setAmount(product.getAmount() - amount);
            boolean isUpdated = update(product, product);
            if (!isUpdated) {
                return false;
            }
        } else {
            return false;
        }
        return isDecreased;
    }

    @Override
    public List<Product> search(String search) {
        return repository.search(search);
    }

    @Override
    public List<Product> getTypeDesc(String type) {
        return repository.getTypeDesc(type);
    }

    @Override
    public List<Product> sortByCategory(String[] categories) {
        List<Product> categoryProducts = new ArrayList<>();
        if (categories == null) {
            return null;
        }
        for (String category : categories) {
            categoryProducts.addAll(getCategoryDesc(category));
        }
        return categoryProducts;
    }

    @Override
    public List<Product> sortByType(String[] types) {
        List<Product> typeProducts = new ArrayList<>();
        if (types == null) {
            return null;
        }
        for (String type : types) {
            typeProducts.addAll(getTypeDesc(type));
        }
        return typeProducts;
    }

    @Override
    public List<Product> sort(String[] categories, String[] types) {
        List<Product> sortProducts = new ArrayList<>();
        List<Product> categoryProducts = sortByCategory(categories);
        List<Product> typeProducts = sortByType(types);
        if (categories == null && types == null) {
            return null;
        } else if (categories != null) {
            sortProducts.addAll(categoryProducts);
        }
        if (types != null) {
            sortProducts.addAll(typeProducts);
        }
        sortProducts = sortProducts.stream().distinct().collect(Collectors.toList());
        Comparator<Product> productComparator = new ProductIdDescComparator();
        sortProducts.sort(productComparator);
        return sortProducts;
    }
}
