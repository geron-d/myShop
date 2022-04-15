package by.it.academy.repository;

import by.it.academy.connections.ConnectionSQL;
import by.it.academy.entities.Product;

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
        try (Connection con = connection.connect()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO products (category, type, name, image, date) VALUES (?,?,?,?,?)");
            statement.setString(1, product.getCategory());
            statement.setString(2, product.getType());
            statement.setString(3, product.getName());
            statement.setString(4, product.getImage_path());
            statement.setDate(5, Date.valueOf(product.getLocalDate()));
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public Product get(Product product) {
        Product thisProduct;
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(product.getName())) {
                    int id = resultSet.getInt("id");
                    String category = resultSet.getString("category");
                    String type = resultSet.getString("type");
                    String name = resultSet.getString("name");
                    String image = resultSet.getString("image");
                    Date date = resultSet.getDate("date");
                    thisProduct = new Product(id, category, type, name, image, date.toLocalDate());
                    connection.close();
                    return thisProduct;
                }
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
        return null;
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
                Product product = new Product(id, category, type, name, image, date.toLocalDate());
                products.add(product);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
        return products;
    }
}
