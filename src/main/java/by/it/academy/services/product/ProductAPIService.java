package by.it.academy.services.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductRepository;

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
        if (product.getAmount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllDesc() {
        return repository.getAllDesc();
    }


}
