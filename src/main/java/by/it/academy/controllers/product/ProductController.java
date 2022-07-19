package by.it.academy.controllers.product;

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

/**
 * Controller view product.
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = "/product")
public class ProductController extends DefaultController {
    Logger log = Logger.getLogger(ProductController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/product - method: get - id: " + id);

        Product product = productService.getProductById(id).get();
        log.info("/product - method: get - product: " + product);

        session.setAttribute("product", product);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }
}
