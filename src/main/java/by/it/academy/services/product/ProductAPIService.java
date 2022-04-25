package by.it.academy.services.product;

import by.it.academy.Paths;
import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;

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
        Product product = new Product(id);
        return get(product);
    }

    @Override
    public List<Product> getLastProducts(int amount) {
        List<Product> products = getAll();
        List<Product> lastProducts = new ArrayList<>();
        if (products.size() >= amount) {
            for (int i = products.size() - 1; i > products.size()-amount-1 ; i--) {
                lastProducts.add(products.get(i));
            }
        } else {
            lastProducts.addAll(products);
        }
        return lastProducts;
    }

    @Override
    public List<Product> getHeadphones() {
        List<Product> products = getAll();
        List<Product> headphones = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(Paths.PRODUCT_TYPE_HEADPHONES)) {
                headphones.add(product);
            }
        }
        return headphones;
    }

    @Override
    public boolean isProductGetAmount(Product product) {
        if (product.getAmount() > 0) {
            return true;
        }
        return false;
    }


}
