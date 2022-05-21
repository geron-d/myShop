package by.it.academy.controllers;

import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.bucket.BucketAPIRepository;
import by.it.academy.repositories.bucket.BucketRepository;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.repositories.user.UserAPIRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.bucket.BucketAPIService;
import by.it.academy.services.bucket.BucketService;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.user.UserAPIService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class DefaultController extends HttpServlet {
    private final ConnectionSQL connection = new ConnectionMySQL();

    protected UserService<User> createUserAPIService() {
        final UserRepository<User> userRepository = new UserAPIRepository(connection);
        return new UserAPIService(userRepository);
    }

    protected ProductService<Product> createProductAPIService() {
        final ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
        return new ProductAPIService(productAPIRepository);
    }

    protected BucketService<Bucket> createBucketAPIService(ProductService<Product> productService) {
        final BucketRepository<Bucket> bucketRepository = new BucketAPIRepository(connection);
        return new BucketAPIService(bucketRepository, productService);
    }

    protected void logUserInSession(HttpSession session, Logger log) {
        User user = (User) session.getAttribute("user");
        log.info("userInSession: " + user);
    }

    protected Product getProductByParams(HttpServletRequest req) {
        return Product.builder()
                .category(req.getParameter("category"))
                .type(req.getParameter("type"))
                .name(req.getParameter("name"))
                .image_path(req.getParameter("image"))
                .localDate(LocalDate.now())
                .producer(req.getParameter("producer"))
                .amount(Integer.parseInt(req.getParameter("amount")))
                .price(Double.parseDouble(req.getParameter("price")))
                .build();
    }
}
