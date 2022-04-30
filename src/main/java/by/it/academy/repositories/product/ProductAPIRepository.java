package by.it.academy.repositories.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connections.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductAPIRepository implements ProductRepository<Product> {
    private final ConnectionSQL connection;

    public ProductAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product product) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO products (category, type, name, image, date, producer, amount, price) " +
                    "VALUES (?,?,?,?,?,?,?,?)");
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
            return false;
        }
    }

    @Override
    public Product get(Product product) {
        Product thisProduct = new Product();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products " +
                    "WHERE id = ?");
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
                thisProduct = new Product(id, category, type, name, image, date.toLocalDate(), producer, amount, price);
                return thisProduct;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return thisProduct;
        }
        return thisProduct;
    }

    @Override
    public boolean update(Product product, Product newProduct) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE products SET category=?, type=?, name=?, image=?, date=?, producer=?, amount=?, price=? " +
                    "WHERE id=?");
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
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM products WHERE id=?");
            statement.setInt(1, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products");
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
                Product product = new Product(id, category, type, name, image, date.toLocalDate(), producer, amount, price);
                products.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return products;
        }
        return products;
    }

    @Override
    public List<Product> getLastProducts(int amount) {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products ORDER BY id DESC LIMIT ?");
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
                Product product = new Product(id, category, type, name, image, date.toLocalDate(), producer, thisAmount, price);
                products.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return products;
        }
        return products;
    }

    public List<Product> getCategoryDesc(String category) {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE category = ? ORDER BY id DESC");
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
                Product product = new Product(id, thisCategory, type, name, image, date.toLocalDate(), producer, thisAmount, price);
                products.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return products;
        }
        return products;
    }

    public Product getByID(int id) {
        Product thisProduct = new Product();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
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
                thisProduct = new Product(thisId, category, type, name, image, date.toLocalDate(), producer, amount, price);
                connection.close();
                return thisProduct;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return thisProduct;
        }
        return thisProduct;
    }

    public List<Product> getAllDesc() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM products ORDER BY id DESC ");
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
                Product product = new Product(id, category, type, name, image, date.toLocalDate(), producer, amount, price);
                products.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return products;
        }
        return products;
    }

}
