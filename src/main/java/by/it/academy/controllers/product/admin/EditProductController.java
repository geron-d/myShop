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
 * Controller perform editing product in to the store.
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = "/products/edit")
public class EditProductController extends DefaultController {
    Logger log = Logger.getLogger(EditProductController.class);
    CategoryService<Category> categoryService = createCategoryAPIService();
    TypeService<Type> typeService = createTypeAPIService();
    ProducerService<Producer> producerService = createProducerAPIService();
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/products/edit - method: post - id: " + id);

        Product product = productService.getProductById(id).get();
        log.info("/products/edit - method: post - product: " + product);

        final Product newProduct = getProductByParams(req, categoryService, typeService, producerService);
        log.info("/products/edit - method: post - newProduct: " + newProduct);

        productService.saveProduct(newProduct);
        log.info("/products/edit - method: post - saveProduct: " + newProduct);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_UPDATED_PATH);
        requestDispatcher.forward(req, resp);
    }
}
