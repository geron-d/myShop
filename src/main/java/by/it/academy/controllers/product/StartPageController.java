package by.it.academy.controllers.product;

import by.it.academy.contants.Constants;
import by.it.academy.contants.Order;
import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
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
import java.util.Objects;

/**
 * Controller view start page.
 * On the start page view 4 last added products/
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = "/start")
public class StartPageController extends DefaultController {
    Logger log = Logger.getLogger(StartPageController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/start - method: get - user: " + user);

        List<Product> lastProducts = productService.getLastProducts(Constants.AMOUNT_PRODUCTS_ON_START_PAGE, Order.DESC);
        log.info("/start - method: get - lastProducts: " + lastProducts);

        req.setAttribute("lastProducts", lastProducts);

        final RequestDispatcher requestDispatcher = Objects.isNull(user)
                ? req.getRequestDispatcher(Paths.START_PAGE_USER_PATH)
                : user.getAccessLevel().equals(AccessLevel.USER)
                ? req.getRequestDispatcher(Paths.START_PAGE_USER_PATH)
                : req.getRequestDispatcher(Paths.START_PAGE_ADMIN_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
