package by.it.academy.controllers.product;

import by.it.academy.contants.Constants;
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

@WebServlet(urlPatterns = "/headphones")
public class HeadphonesController extends DefaultController {
    Logger log = Logger.getLogger(HeadphonesController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        List<Product> headphones = productService.getProductsInCategory(Constants.CATEGORY_HEADPHONES, Order.DESC);
        log.info("/headphones - method: get - headphones: " + headphones);

        req.setAttribute("headphones", headphones);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.HEADPHONES_PATH);
        requestDispatcher.forward(req, resp);
    }
}
