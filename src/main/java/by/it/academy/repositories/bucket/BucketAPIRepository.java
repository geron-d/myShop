package by.it.academy.repositories.bucket;

import by.it.academy.contants.SQL;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionSQL;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BucketAPIRepository implements BucketRepository<Bucket> {
    Logger log = Logger.getLogger(BucketAPIRepository.class);
    private final ConnectionSQL connection;

    public BucketAPIRepository(ConnectionSQL connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Bucket bucket) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_INSERT_SQL);
            statement.setInt(1, bucket.getUserId());
            statement.setInt(2, bucket.getProductId());
            statement.setInt(3, bucket.getAmount());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: create: " + e);
            return false;
        }
    }

    @Override
    public Optional<Bucket> get(Bucket bucket) {
        Optional<Bucket> thisBucket = Optional.of(new Bucket());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_GET_SQL);
            statement.setInt(1, bucket.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                thisBucket = Optional.ofNullable(Bucket.builder()
                        .id(thisId)
                        .userId(userId)
                        .productId(productId)
                        .amount(amount)
                        .build());
                return thisBucket;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: get: " + e);
            return thisBucket;
        }
        return thisBucket;
    }

    @Override
    public boolean update(Bucket bucket, Bucket newBucket) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_UPDATE_SQL);
            statement.setInt(1, newBucket.getUserId());
            statement.setInt(2, newBucket.getProductId());
            statement.setInt(3, newBucket.getAmount());
            statement.setInt(4, bucket.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: update: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(Bucket bucket) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_DELETE_SQL);
            statement.setInt(1, bucket.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: delete: " + e);
            return false;
        }
    }

    @Override
    public List<Optional<Bucket>> getAllBucket() {
        List<Optional<Bucket>> bucket = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_GET_ALL_BUCKET_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                Bucket thisBucket = Bucket.builder()
                        .id(thisId)
                        .userId(userId)
                        .productId(productId)
                        .amount(amount)
                        .build();
                bucket.add(Optional.ofNullable(thisBucket));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: getAllBucket: " + e);
            return bucket;
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> getByUserAndProduct(User user, Product product) {
        Optional<Bucket> bucket = Optional.of(new Bucket());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_GET_BY_USER_AND_PRODUCT_SQL);
            statement.setInt(1, user.getId());
            statement.setInt(2, product.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                bucket = Optional.ofNullable(Bucket.builder()
                        .id(thisId)
                        .userId(userId)
                        .productId(productId)
                        .amount(amount)
                        .build());
                return bucket;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: getByUserAndProduct: " + e);
            return bucket;
        }
        return bucket;
    }

    @Override
    public List<Optional<Bucket>> getByUser(User user) {
        List<Optional<Bucket>> bucket = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_GET_BY_USER_SQL);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                Bucket thisBucket = Bucket.builder()
                        .id(thisId)
                        .userId(userId)
                        .productId(productId)
                        .amount(amount)
                        .build();
                bucket.add(Optional.ofNullable(thisBucket));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: getByUser: " + e);
            return bucket;
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> getById(int id) {
        Optional<Bucket> thisBucket = Optional.of(new Bucket());
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement(SQL.BUCKET_GET_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int thisId = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                int amount = resultSet.getInt("amount");
                thisBucket = Optional.ofNullable(Bucket.builder()
                        .id(thisId)
                        .userId(userId)
                        .productId(productId)
                        .amount(amount)
                        .build());
                return thisBucket;
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.info("BucketAPIRepository - method: getByUser: " + e);
            return thisBucket;
        }
        return thisBucket;
    }

}
