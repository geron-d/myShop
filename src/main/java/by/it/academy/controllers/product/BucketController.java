package by.it.academy.controllers.product;

import by.it.academy.contants.Constants;
import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.productInBucket.ProductInBucketService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/bucket")
public class BucketController extends DefaultController {
    Logger log = Logger.getLogger(BucketController.class);
    ProductService<Product> productService = createProductAPIService();
    ProductInBucketService<ProductInBucket> productInBucketService = createProductInBucketAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/bucket - method: get - user: " + user);

        List<ProductInBucket> productsInBucket = user.getBucket();
        log.info("/bucket - method: get - productsInBucket: " + productsInBucket);

        session.setAttribute("productsInBucket", productsInBucket);

        boolean isBucketEmpty = !productsInBucket.isEmpty();
        log.info("/bucket - method: get - isBucketEmpty: " + isBucketEmpty);

        session.setAttribute("isBucketEmpty", isBucketEmpty);

        double allCost = productInBucketService.getAllCost(productsInBucket);
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

                Optional<Product> product = productService.getProductById(id);
                log.info("/bucket - method: post - product: " + product);

                if (product.isPresent()) {
                    productInBucketService.deleteAmountProducts(productsInBucket, product.get(),
                            Constants.AMOUNT_PRODUCT_DELETED_WHEN_USER_PULL_DELETE);
                    log.info("/bucket - method: post - deleteAmountProducts: " + productsInBucket + " "
                            + product.get());
                }

                req.setAttribute("product", product);

                productsInBucket = productInBucketService.getProductInBucketByUser(user);
                log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                session.setAttribute("productsInBucket", productsInBucket);

                boolean isBucketEmpty = !productsInBucket.isEmpty();
                log.info("/bucket - method: post - isBucketEmpty: " + isBucketEmpty);

                session.setAttribute("isBucketEmpty", isBucketEmpty);

                double allCost = productInBucketService.getAllCost(productsInBucket);
                log.info("/bucket - method: post - allCost: " + allCost);

                session.setAttribute("allCost", allCost);

                final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_DELETED_FROM_BUCKET_PATH);

                requestDispatcher.forward(req, resp);
                break;
            }
            case "deleteAll": {
                productInBucketService.deleteAllProducts(productsInBucket);
                log.info("/bucket - method: post - deleteAllProducts: " + productsInBucket);

                productsInBucket = productInBucketService.getProductInBucketByUser(user);
                log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                session.setAttribute("productsInBucket", productsInBucket);

                boolean isBucketEmpty = !productsInBucket.isEmpty();
                log.info("/bucket - method: post - isBucketEmpty: " + isBucketEmpty);

                session.setAttribute("isBucketEmpty", isBucketEmpty);

                double allCost = productInBucketService.getAllCost(productsInBucket);
                log.info("/bucket - method: post - allCost: " + allCost);

                session.setAttribute("allCost", allCost);

                final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ALL_PRODUCTS_DELETED_FROM_BUCKET_PATH);

                requestDispatcher.forward(req, resp);
                break;
            }
            case "buy": {
                productInBucketService.deleteAllProducts(productsInBucket);
                log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                productInBucketService.buy(productsInBucket);
                log.info("/bucket - method: post - buy: " + productsInBucket);


                productsInBucket = productInBucketService.getProductInBucketByUser(user);
                log.info("/bucket - method: post - productsInBucket: " + productsInBucket);

                session.setAttribute("productsInBucket", productsInBucket);

                double allCost = productInBucketService.getAllCost(productsInBucket);
                log.info("/bucket - method: post - allCost: " + allCost);

                session.setAttribute("allCost", allCost);

                final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCTS_BOUGHT_PATH);

                requestDispatcher.forward(req, resp);
                break;
            }
        }
    }
}
