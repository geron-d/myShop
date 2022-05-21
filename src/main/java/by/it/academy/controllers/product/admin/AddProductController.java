package by.it.academy.controllers.product.admin;

import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.Product;
import by.it.academy.services.product.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/products/add")
public class AddProductController extends DefaultController {
    Logger log = Logger.getLogger(AddProductController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        logUserInSession(session, log);

        final RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(Paths.ADD_PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        final Product product = getProductByParams(req);
        log.info("/products/add - method: post - product: " + product);

        boolean isProductCreated = productService.create(product);
        log.info("/products/add - method: post - isProductCreated: " + isProductCreated);

        final RequestDispatcher requestDispatcher = isProductCreated
                ? req.getRequestDispatcher(Paths.PRODUCT_ADDED_PATH)
                : req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
        requestDispatcher.forward(req, resp);
    }
}
