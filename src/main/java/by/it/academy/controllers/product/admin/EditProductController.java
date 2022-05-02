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

@WebServlet(urlPatterns = "/products/edit")
public class EditProductController extends HttpServlet {
    Logger log = Logger.getLogger(EditProductController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getByID(id);
        log.info(product);

        final String category = req.getParameter("category");
        final String type = req.getParameter("type");
        final String name = req.getParameter("name");
        final String image = req.getParameter("image");
        final String producer = req.getParameter("producer");
        final int amount = Integer.parseInt(req.getParameter("amount"));
        final double price = Double.parseDouble(req.getParameter("price"));
        final Product newProduct = new Product(category, type, name, image, product.getLocalDate(), producer, amount, price);
        log.info(newProduct);

        boolean isUpdated = productService.update(product, newProduct);
        if (isUpdated) {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_UPDATED_PATH);
            requestDispatcher.forward(req, resp);
        }
    }
}
