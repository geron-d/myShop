package by.it.academy.controllers.product;

import by.it.academy.Paths;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
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

@WebServlet(urlPatterns = "/product")
public class ProductController extends HttpServlet {
    Logger log = Logger.getLogger(ProductController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);
    BucketRepository<Bucket> bucketRepository = new BucketAPIRepository(connection);
    BucketService<Bucket> bucketService = new BucketAPIService(bucketRepository, productService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getByID(id);
        session.setAttribute("product", product);
        log.info(product);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info(user);

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getByID(id);
        req.setAttribute("product", product);
        log.info(product);

        boolean isAdded = bucketService.add(user, product);
        final RequestDispatcher requestDispatcher;
        if (isAdded) {
            requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_ADDED_TO_BUCKET_PATH);
        } else {
            requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_IS_NOT_ADDED_TO_BUCKET_PATH);
        }
        requestDispatcher.forward(req, resp);
    }
}
