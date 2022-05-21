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

@WebServlet(urlPatterns = "/products/product/admin")
public class AdminProductController extends DefaultController {
    Logger log = Logger.getLogger(AdminProductController.class);
    ProductService<Product> productService = createProductAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/products/product/admin - method: get - id: " + id);

        Product product = productService.getByID(id);
        log.info("/products/product/admin - method: get - product: " + product);

        session.setAttribute("product", product);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ADMIN_PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        logUserInSession(session, log);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/products/product/admin - method: post - id: " + id);

        Product product = productService.getByID(id);
        log.info("/products/product/admin - method: post - product: " + product);

        req.setAttribute("product", product);

        String submit = req.getParameter("submit");
        log.info("/products/product/admin - method: post - submit: " + submit);

        switch (submit) {
            case "edit": {
                final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.EDIT_PRODUCT_PATH);
                requestDispatcher.forward(req, resp);
                break;
            }
            case "delete": {
                boolean isProductDeleted = productService.delete(product);
                log.info("/products/product/admin - method: post - isDeleted: " + isProductDeleted);

                final RequestDispatcher requestDispatcher = isProductDeleted
                        ? req.getRequestDispatcher(Paths.PRODUCT_DELETED_PATH)
                        : req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
                requestDispatcher.forward(req, resp);
                break;
            }
        }
    }
}
