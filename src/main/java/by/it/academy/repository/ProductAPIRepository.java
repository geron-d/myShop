package by.it.academy.repository;

import by.it.academy.connections.ConnectionSQL;
import by.it.academy.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductAPIRepository implements ProductRepository<Product> {
    private final ConnectionSQL connection;

    public ProductAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product product) {
        try (Connection con = connection.connect()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO products (category, type, name, image, date, producer, amount, price) VALUES (?,?,?,?,?,?,?,?)");
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
    public Optional<Product> get(Product product) {
        Product thisProduct;
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                if (resultSet.getInt("id") == product.getId()) {
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
                    connection.close();
                    return Optional.of(thisProduct);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Product product, Product newProduct) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE products SET category=?, type=?, name=?, image=?, date=?, producer=?, amount=?, price=? WHERE id=?");
            statement.setString(1, newProduct.getCategory());
            statement.setString(2, newProduct.getType());
            statement.setString(3, newProduct.getName());
            statement.setString(4, newProduct.getImage_path());
            statement.setDate(5, Date.valueOf(newProduct.getLocalDate()));
            statement.setString(6, newProduct.getProducer());
            statement.setInt(7,newProduct.getAmount());
            statement.setDouble(8, newProduct.getPrice());
            statement.setInt(9, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
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
            return null;
        }
        return products;
    }
}
