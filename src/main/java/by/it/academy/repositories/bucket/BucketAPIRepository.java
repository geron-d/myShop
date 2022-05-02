package by.it.academy.repositories.bucket;

import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BucketAPIRepository implements BucketRepository<Bucket> {
    private final ConnectionSQL connection;

    public BucketAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Bucket bucket) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO bucket (userId, productId, amount) VALUES (?,?,?)");
            statement.setInt(1, bucket.getUserId());
            statement.setInt(2, bucket.getProductId());
            statement.setInt(3, bucket.getAmount());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public Bucket get(Bucket bucket) {
        Bucket thisBucket = new Bucket();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bucket WHERE id = ?");
            statement.setInt(1, bucket.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                thisBucket = new Bucket(thisId, userId, productId, amount);
                return thisBucket;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return thisBucket;
        }
        return thisBucket;
    }

    @Override
    public boolean update(Bucket bucket, Bucket newBucket) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE bucket SET userId=?, productId=?, amount=? WHERE id=?");
            statement.setInt(1, newBucket.getUserId());
            statement.setInt(2, newBucket.getProductId());
            statement.setInt(3, newBucket.getAmount());
            statement.setInt(4, bucket.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Bucket bucket) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM bucket WHERE id=?");
            statement.setInt(1, bucket.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> bucket = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bucket");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                Bucket thisBucket = new Bucket(thisId, userId, productId, amount);
                bucket.add(thisBucket);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return bucket;
        }
        return bucket;
    }

    @Override
    public Bucket getByUserAndProduct(User user, Product product) {
        Bucket bucket = new Bucket();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bucket " +
                    "WHERE userId = ? AND productId = ?");
            statement.setInt(1, user.getId());
            statement.setInt(2, product.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                bucket = new Bucket(thisId, userId, productId, amount);
                return bucket;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return bucket;
        }
        return bucket;
    }

    @Override
    public List<Bucket> getByUser(User user) {
        List<Bucket> bucket = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bucket WHERE userId = ?");
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                Bucket thisBucket = new Bucket(thisId, userId, productId, amount);
                bucket.add(thisBucket);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return bucket;
        }
        return bucket;
    }

    @Override
    public Bucket getById(int id) {
        Bucket thisBucket = new Bucket();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bucket WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                thisBucket = new Bucket(thisId, userId, productId, amount);
                return thisBucket;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return thisBucket;
        }
        return thisBucket;
    }

}
