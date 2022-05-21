package by.it.academy.controllers.product.admin;

import by.it.academy.contants.Order;
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
import java.util.List;

@WebServlet(urlPatterns = "/products/admin")
public class AdminAllProductsController extends DefaultController {
    Logger log = Logger.getLogger(AdminAllProductsController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        List<Product> products = productService.getAllProducts(Order.DESC);
        log.info("/products/admin - method: get - products: " + products);
        req.setAttribute("products", products);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ADMIN_ALL_PRODUCTS_PATH);
        requestDispatcher.forward(req, resp);
    }
}
