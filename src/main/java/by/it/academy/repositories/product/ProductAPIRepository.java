package by.it.academy.repositories.product;

import by.it.academy.contants.Order;
import by.it.academy.contants.SQL;
import by.it.academy.entities.Product;
import by.it.academy.repositories.connections.ConnectionSQL;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductAPIRepository implements ProductRepository<Product> {
    Logger log = Logger.getLogger(ProductAPIRepository.class);
    private final ConnectionSQL connection;

    public ProductAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product product) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_INSERT_SQL);
            statement.setString(1, product.getCategory());
            statement.setString(2, product.getType());
            statement.setString(3, product.getName());
            statement.setString(4, product.getImage_path());
            statement.setDate(5, Date.valueOf(product.getLocalDate()));
            statement.setString(6, product.getProducer());
            statement.setInt(7, product.getAmount());
            statement.setDouble(8, product.getPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: create: " + e);
            return false;
        }
    }

    @Override
    public Optional<Product> get(Product product) {
        Optional<Product> thisProduct = Optional.of(new Product());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_GET_SQL);
            statement.setInt(1, product.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                thisProduct = Optional.ofNullable(Product.builder()
                        .id(id)
                        .category(category)
                        .type(type)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(amount)
                        .price(price)
                        .build());
                return thisProduct;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: get: " + e);
            return thisProduct;
        }
        return thisProduct;
    }

    @Override
    public boolean update(Product product, Product newProduct) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_UPDATE_SQL);
            statement.setString(1, newProduct.getCategory());
            statement.setString(2, newProduct.getType());
            statement.setString(3, newProduct.getName());
            statement.setString(4, newProduct.getImage_path());
            statement.setDate(5, Date.valueOf(newProduct.getLocalDate()));
            statement.setString(6, newProduct.getProducer());
            statement.setInt(7, newProduct.getAmount());
            statement.setDouble(8, newProduct.getPrice());
            statement.setInt(9, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: update: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_DELETE_SQL);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: delete: " + e);
            return false;
        }
    }

    @Override
    public List<Optional<Product>> getAllProducts(Order order) {
        List<Optional<Product>> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_GET_ALL_PRODUCTS_SQL + order);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                Product product = Product.builder()
                        .id(id)
                        .category(category)
                        .type(type)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(amount)
                        .price(price)
                        .build();
                products.add(Optional.ofNullable(product));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: getAllProducts: " + e);
            return products;
        }
        return products;
    }

    @Override
    public List<Optional<Product>> getLastProducts(int amount, Order order) {
        List<Optional<Product>> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_GET_ALL_PRODUCTS_SQL + order + SQL.LIMIT_SQL);
            statement.setInt(1, amount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int thisAmount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                Product product = Product.builder()
                        .id(id)
                        .category(category)
                        .type(type)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(thisAmount)
                        .price(price)
                        .build();
                products.add(Optional.ofNullable(product));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: getLastProducts: " + e);
            return products;
        }
        return products;
    }

    @Override
    public List<Optional<Product>> getProductsInCategory(String category, Order order) {
        List<Optional<Product>> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_GET_PRODUCTS_IN_CATEGORY_SQL + order);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String thisCategory = resultSet.getString("category");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int thisAmount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                Product product = Product.builder()
                        .id(id)
                        .category(thisCategory)
                        .type(type)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(thisAmount)
                        .price(price)
                        .build();
                products.add(Optional.ofNullable(product));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: getProductsInCategory: " + e);
            return products;
        }
        return products;
    }

    @Override
    public Optional<Product> getByID(int id) {
        Optional<Product> thisProduct = Optional.of(new Product());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_GET_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                thisProduct = Optional.ofNullable(Product.builder()
                        .id(thisId)
                        .category(category)
                        .type(type)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(amount)
                        .price(price)
                        .build());
                return thisProduct;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: getByID: " + e);
            return thisProduct;
        }
        return thisProduct;
    }

    @Override
    public List<Optional<Product>> search(String search) {
        List<Optional<Product>> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products " +
                    "WHERE LOWER(name) like LOWER('%" + search + "%') " +
                    "OR LOWER(category) like LOWER('%" + search + "%') " +
                    "OR LOWER(type) like LOWER('%" + search + "%') " +
                    "ORDER BY id DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                Product product = Product.builder()
                        .id(id)
                        .category(category)
                        .type(type)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(amount)
                        .price(price)
                        .build();
                products.add(Optional.ofNullable(product));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: search: " + e);
            return products;
        }
        return products;
    }

    @Override
    public List<Optional<Product>> getProductsInType(String type, Order order) {
        List<Optional<Product>> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.PRODUCT_GET_PRODUCTS_IN_TYPE_SQL + order);
            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String thisCategory = resultSet.getString("category");
                String thisType = resultSet.getString("type");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Date date = resultSet.getDate("date");
                String producer = resultSet.getString("producer");
                int thisAmount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                Product product = Product.builder()
                        .id(id)
                        .category(thisCategory)
                        .type(thisType)
                        .name(name)
                        .image_path(image)
                        .localDate(date.toLocalDate())
                        .producer(producer)
                        .amount(thisAmount)
                        .price(price)
                        .build();
                products.add(Optional.ofNullable(product));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("ProductAPIRepository - method: getProductsInType: " + e);
            return products;
        }
        return products;
    }

}
