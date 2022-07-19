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
import java.util.Arrays;
import java.util.List;

/**
 * Controller perform sort products by category or type for admin.
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = {"/products/sort/admin"})
public class AdminSortController extends DefaultController {
    Logger log = Logger.getLogger(AdminSortController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        final String[] categories = req.getParameterValues("category");
        log.info("/sort - method: get - categories: " + Arrays.toString(categories));

        final String[] types = req.getParameterValues("type");
        log.info("/sort - method: get - types: " + Arrays.toString(types));

        List<Product> products = productService.sort(categories, types);
        log.info("/search - method: get - products: " + products);

        req.setAttribute("products", products);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ADMIN_SORT_PATH);
        requestDispatcher.forward(req, resp);
    }
}
