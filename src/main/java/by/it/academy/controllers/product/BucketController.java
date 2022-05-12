package by.it.academy.controllers.product;

import by.it.academy.contants.Constants;
import by.it.academy.contants.Paths;
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
import by.it.academy.services.bucket.BucketAPIService;
import by.it.academy.services.bucket.BucketService;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;
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

@WebServlet(urlPatterns = "/bucket")
public class BucketController extends HttpServlet {
    Logger log = Logger.getLogger(BucketController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);
    BucketRepository<Bucket> bucketRepository = new BucketAPIRepository(connection);
    BucketService<Bucket> bucketService = new BucketAPIService(bucketRepository, productService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/bucket - method: get - user: " + user);

        List<ProductInBucket> productsInBucket = bucketService.getProductsInBucket(user);
        log.info("/bucket - method: get - productsInBucket: " + productsInBucket);

        session.setAttribute("productsInBucket", productsInBucket);

        boolean isBucketEmpty = !productsInBucket.isEmpty();
        log.info("/bucket - method: get - isBucketEmpty: " + isBucketEmpty);

        session.setAttribute("isBucketEmpty", isBucketEmpty);

        double allCost = bucketService.getAllCost(productsInBucket);
        log.info("/bucket - method: get - allCost: " + allCost);

        session.setAttribute("allCost", allCost);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.BUCKET_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/bucket - method: post - user: " + user);

        List<ProductInBucket> productsInBucket = (List<ProductInBucket>) session.getAttribute("productsInBucket");
        log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

        String submit = req.getParameter("submit");
        log.info("/bucket - method: post - submit: " + submit);

        switch (submit) {
            case "delete": {
                int id = Integer.parseInt(req.getParameter("id"));
                log.info("/bucket - method: post - id: " + id);

                Product product = productService.getByID(id);
                log.info("/bucket - method: post - product: " + product);

                boolean isDeleted = bucketService.deleteAmountProducts(productsInBucket, product, Constants.AMOUNT_PRODUCT_DELETED_WHEN_USER_PULL_DELETE);
                log.info("/bucket - method: post - isDeleted: " + isDeleted);

                final RequestDispatcher requestDispatcher;
                if (isDeleted) {
                    log.info("/bucket - method: post - product: " + product);

                    req.setAttribute("product", product);

                    productsInBucket = bucketService.getProductsInBucket(user);
                    log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                    session.setAttribute("productsInBucket", productsInBucket);

                    boolean isBucketEmpty = !productsInBucket.isEmpty();
                    log.info("/bucket - method: post - isBucketEmpty: " + isBucketEmpty);

                    session.setAttribute("isBucketEmpty", isBucketEmpty);

                    double allCost = bucketService.getAllCost(productsInBucket);
                    log.info("/bucket - method: post - allCost: " + allCost);

                    session.setAttribute("allCost", allCost);

                    requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_DELETED_FROM_BUCKET_PATH);
                } else {
                    requestDispatcher = req.getRequestDispatcher(Paths.DATA_BASE_ERROR);

                }
                requestDispatcher.forward(req, resp);
                break;
            }
            case "deleteAll": {
                boolean isDeleted = bucketService.deleteAllProducts(productsInBucket);
                log.info("/bucket - method: post - isDeleted: " + isDeleted);

                final RequestDispatcher requestDispatcher;
                if (isDeleted) {
                    productsInBucket = bucketService.getProductsInBucket(user);
                    log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                    session.setAttribute("productsInBucket", productsInBucket);

                    boolean isBucketEmpty = !productsInBucket.isEmpty();
                    log.info("/bucket - method: post - isBucketEmpty: " + isBucketEmpty);

                    session.setAttribute("isBucketEmpty", isBucketEmpty);

                    double allCost = bucketService.getAllCost(productsInBucket);
                    log.info("/bucket - method: post - allCost: " + allCost);

                    session.setAttribute("allCost", allCost);

                    requestDispatcher = req.getRequestDispatcher(Paths.ALL_PRODUCTS_DELETED_FROM_BUCKET_PATH);
                } else {
                    requestDispatcher = req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
                }
                requestDispatcher.forward(req, resp);
                break;
            }
            case "buy": {
                boolean isBought = bucketService.buy(productsInBucket);
                log.info("/bucket - method: post - isBought: " + isBought);

                final RequestDispatcher requestDispatcher;
                if (isBought) {
                    productsInBucket = bucketService.getProductsInBucket(user);
                    log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                    session.setAttribute("productsInBucket", productsInBucket);

                    double allCost = bucketService.getAllCost(productsInBucket);
                    log.info("/bucket - method: post - allCost: " + allCost);

                    session.setAttribute("allCost", allCost);

                    requestDispatcher = req.getRequestDispatcher(Paths.PRODUCTS_BOUGHT_PATH);
                } else {
                    requestDispatcher = req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
                }
                requestDispatcher.forward(req, resp);
                break;
            }
        }
    }
}
