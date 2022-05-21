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

@WebServlet(urlPatterns = "/products/edit")
public class EditProductController extends DefaultController {
    Logger log = Logger.getLogger(EditProductController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/products/edit - method: post - id: " + id);

        Product product = productService.getByID(id);
        log.info("/products/edit - method: post - product: " + product);

        final Product newProduct = getProductByParams(req);
        log.info("/products/edit - method: post - newProduct: " + newProduct);

        boolean isProductUpdated = productService.update(product, newProduct);
        log.info("/products/edit - method: post - isUpdated: " + isProductUpdated);

        final RequestDispatcher requestDispatcher = isProductUpdated
                ? req.getRequestDispatcher(Paths.PRODUCT_UPDATED_PATH)
                : req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
        requestDispatcher.forward(req, resp);
    }
}
