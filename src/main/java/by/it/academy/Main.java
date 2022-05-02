package by.it.academy;

import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.User;
import by.it.academy.repositories.bucket.BucketAPIRepository;
import by.it.academy.repositories.bucket.BucketRepository;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.entities.Product;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.repositories.user.UserAPIRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ConnectionSQL connection = new ConnectionMySQL();
        ProductRepository<Product> repository = new ProductAPIRepository(connection);
        ProductService<Product> service = new ProductAPIService(repository);
        LocalDate localDate = LocalDate.now();
        Product F9Old = new Product(1,"headphones", "wireless", "F9 Bluetooth 5.2", "/images/1.jpg",
                localDate, "F9", 100, 34.90);
//        Product F9 = new Product("headphones", "wireless", "F9 Bluetooth 5.2", "/images/1.jpg",
//                localDate, "F9", 100, 34.90);
//        Product defenderOld = new Product(2,"headphones", "wireless overhead", "Defender FreeMotion", "/images/2.jpg",
//                localDate, "Defender", 100, 34.83);
//        Product defender = new Product("headphones", "wireless overhead", "Defender FreeMotion", "/images/2.jpg",
//                localDate, "Defender", 100, 34.83);
//
//        Product GQBox = new Product("headphones", "wired", "GQBox", "/images/3.jpg",
//                localDate, "GQBox", 100, 9.43);
//
        service.getCategoryDesc(Paths.CATEGORY_HEADPHONES)
                        .stream()
                        .forEach(System.out::println);

        UserRepository<User> repositoryUser = new UserAPIRepository(connection);
        User admin = new User(1,"admin","12345", AccessLevel.ADMIN);
//        User adminNew = new User(1,"admin","i'm admin", AccessLevel.ADMIN);
        User user = new User(2, "user", "12345", AccessLevel.USER);
//
//        repositoryUser.readAll()
//                .stream()
//                .forEach(System.out::println);

        BucketRepository<Bucket> bucketRepository = new BucketAPIRepository(connection);
        Bucket bucket = new Bucket(1,1, 1, 1);
        Bucket newBucket = new Bucket(1, 2, 3, 4);

//        System.out.println(repositoryUser.get(admin));
    }
}
