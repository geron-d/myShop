package by.it.academy.controllers.product;

import by.it.academy.Paths;
import by.it.academy.contants.Order;
import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductRepository;
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
import java.util.Objects;

@WebServlet(urlPatterns = "/start")
public class StartPageController extends HttpServlet {
    Logger log = Logger.getLogger(StartPageController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/start - method: get - user: " + user);

        List<Product> lastProducts = productService.getLastProducts(Paths.AMOUNT_PRODUCTS_ON_START_PAGE, Order.DESC);
        log.info("/start - method: get - lastProducts: " + lastProducts);

        req.setAttribute("lastProducts", lastProducts);

        final RequestDispatcher requestDispatcher;
        if (Objects.isNull(user)) {
            requestDispatcher = req.getRequestDispatcher(Paths.START_PAGE_USER_PATH);
        } else {
            if (user.getAccessLevel().equals(AccessLevel.USER)) {
                requestDispatcher = req.getRequestDispatcher(Paths.START_PAGE_USER_PATH);
            } else {
                requestDispatcher = req.getRequestDispatcher(Paths.START_PAGE_ADMIN_PATH);
            }
        }
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
