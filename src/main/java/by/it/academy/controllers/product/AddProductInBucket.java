package by.it.academy.controllers.product;

import by.it.academy.contants.Constants;
import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.Product;
import by.it.academy.entities.ProductInBucket;
import by.it.academy.entities.User;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.productInBucket.ProductInBucketService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/product/addInBucket")
public class AddProductInBucket extends DefaultController {
    Logger log = Logger.getLogger(AddProductInBucket.class);
    ProductService<Product> productService = createProductAPIService();
    ProductInBucketService<ProductInBucket> productInBucketService = createProductInBucketAPIService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/product - method: post - user: " + user);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/product - method: post - id: " + id);

        Optional<Product> product = productService.getProductById(id);
        log.info("/product - method: post - product: " + product);

        ProductInBucket productInBucket = new ProductInBucket();

        if (product.isPresent()) {
            req.setAttribute("product", product.get());
            productInBucket = ProductInBucket.builder()
                    .user(user)
                    .product(product.get())
                    .amount(Constants.AMOUNT_PRODUCT_ADDED_WHEN_USER_PULL_ADD_TO_BUCKET)
                    .build();
        }

        productInBucketService.saveProductInBucket(productInBucket);
        log.info("/product - method: post - saveProductInBucket: " + productInBucket);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.PRODUCT_ADDED_TO_BUCKET_PATH);
        requestDispatcher.forward(req, resp);
    }
}
