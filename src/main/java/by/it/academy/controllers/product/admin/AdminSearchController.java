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
import java.util.List;

/**
 * Controller perform search in products for admin.
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = {"/products/search/admin"})
public class AdminSearchController extends DefaultController {
    Logger log = Logger.getLogger(AdminSearchController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        final String search = req.getParameter("search");
        log.info("/search - method: get - search: " + search);

        List<Product> products = productService.search(search);
        log.info("/search - method: get - products: " + products);

        req.setAttribute("products", products);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ADMIN_SEARCH_PATH);
        requestDispatcher.forward(req, resp);
    }
}
