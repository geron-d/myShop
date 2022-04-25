package by.it.academy.controllers.product;

import by.it.academy.Paths;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.BUCKET_PATH);
        requestDispatcher.forward(req, resp);
    }
}
