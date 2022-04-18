package by.it.academy.services.product;

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
    public List<Product> readAll() {
        return repository.readAll();
    }

    public Product getByID(int id) {
        Product product = new Product(id);
        return get(product);
    }

    public List<Product> getLastFour() {
        List<Product> products = readAll();
        List<Product> lastFour = new ArrayList<>();
        if (products.size() >= 4) {
            lastFour.addAll(products.size()-4, products);
        } else {
            lastFour.addAll(products);
        }
        return lastFour;
    }

    public Product buyProduct(Product product) {
        Product buyingProduct;
         if (isProductHas(product)) {
            buyingProduct =new Product(product);
            buyingProduct.setAmount(buyingProduct.getAmount() - 1);
            return buyingProduct;
        }
         return null;
    }

    public boolean isProductHas(Product product) {
        if (product.getAmount() > 0) {
            return true;
        }
        return false;
    }


}
