package by.it.academy.controllers.product.admin;

import by.it.academy.Paths;
import by.it.academy.entities.Product;
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
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/products/add")
public class AddProductController extends HttpServlet {
    Logger log = Logger.getLogger(AddProductController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(Paths.ADD_PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String category = req.getParameter("category");
        final String type = req.getParameter("type");
        final String name = req.getParameter("name");
        final String image = req.getParameter("image");
        final String producer = req.getParameter("producer");
        final int amount = Integer.parseInt(req.getParameter("amount"));
        final double price = Double.parseDouble(req.getParameter("price"));
        final Product product = new Product(category, type, name, image, LocalDate.now(), producer, amount, price);
        log.info(product);

        boolean isCreated = productService.create(product);
        if (isCreated) {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_ADDED_PATH);
            requestDispatcher.forward(req, resp);
        }
    }
}
