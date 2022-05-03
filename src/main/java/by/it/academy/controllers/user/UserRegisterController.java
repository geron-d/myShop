package by.it.academy.controllers.user;

import by.it.academy.Paths;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/create")
public class UserRegisterController extends HttpServlet {
    Logger log = Logger.getLogger(UserRegisterController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    UserRepository<User> userRepository = new UserAPIRepository(connection);
    UserService<User> userService = new UserAPIService(userRepository);
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);
    BucketRepository<Bucket> bucketRepository = new BucketAPIRepository(connection);
    BucketService<Bucket> bucketService = new BucketAPIService(bucketRepository, productService);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        log.info("/user/create - method: post - login: " + login);

        final String password = req.getParameter("password");
        log.info("/user/create - method: post - password: " + password);

        User user = userService.getByLoginPassword(login, password);
        log.info("/user/create - method: post - user: " + user);

        boolean isCreated = userService.create(user);
        log.info("/user/create - method: post - isCreated: " + isCreated);

        final RequestDispatcher requestDispatcher;
        if (isCreated) {
            final HttpSession session = req.getSession();
            session.setAttribute("user", user);

            List<ProductInBucket> productsInBucket = bucketService.getProductsInBucket(user);
            log.info("/user/create - method: post - productsInBucket: " + productsInBucket);

            session.setAttribute("productsInBucket", productsInBucket);

            double allCost = bucketService.getAllCost(productsInBucket);
            log.info("/user/create - method: post - allCost: " + allCost);

            session.setAttribute("allCost", allCost);

            requestDispatcher = req.getRequestDispatcher("/start");
        } else {
            requestDispatcher = req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
        }
        requestDispatcher.forward(req, resp);
    }
}
