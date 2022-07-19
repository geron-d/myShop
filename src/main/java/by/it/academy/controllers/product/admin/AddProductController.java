package by.it.academy.controllers.product.admin;

import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.services.category.CategoryService;
import by.it.academy.services.producer.ProducerService;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.type.TypeService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller perform adding product to the store.
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = "/products/add")
public class AddProductController extends DefaultController {
    Logger log = Logger.getLogger(AddProductController.class);
    CategoryService<Category> categoryService = createCategoryAPIService();
    TypeService<Type> typeService = createTypeAPIService();
    ProducerService<Producer> producerService = createProducerAPIService();
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        logUserInSession(session, log);

        final RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(Paths.ADD_PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        final Product product = getProductByParams(req, categoryService, typeService, producerService);
        log.info("/products/add - method: post - product: " + product);

        productService.saveProduct(product);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_ADDED_PATH);
        requestDispatcher.forward(req, resp);
    }
}
