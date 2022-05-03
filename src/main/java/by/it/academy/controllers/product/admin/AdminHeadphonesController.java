package by.it.academy.controllers.product.admin;

import by.it.academy.Paths;
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

@WebServlet(urlPatterns = "/products/headphones/admin")
public class AdminHeadphonesController extends HttpServlet {
    Logger log = Logger.getLogger(AdminHeadphonesController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/products/headphones/admin - method: get - user: " + user);

        List<Product> headphones = productService.getCategoryDesc(Paths.CATEGORY_HEADPHONES);
        log.info("/products/headphones/admin - method: get - headphones: " + headphones);
        req.setAttribute("headphones", headphones);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ADMIN_HEADPHONES_PATH);
        requestDispatcher.forward(req, resp);
    }
}
