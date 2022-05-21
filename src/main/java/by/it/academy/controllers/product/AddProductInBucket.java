package by.it.academy.controllers.product;

import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.Bucket;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.services.bucket.BucketService;
import by.it.academy.services.product.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/product/addInBucket")
public class AddProductInBucket extends DefaultController {
    Logger log = Logger.getLogger(AddProductInBucket.class);
    ProductService<Product> productService = createProductAPIService();
    BucketService<Bucket> bucketService = createBucketAPIService(productService);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/product - method: post - user: " + user);

        int id = Integer.parseInt(req.getParameter("id"));
        log.info("/product - method: post - id: " + id);

        Product product = productService.getByID(id);
        log.info("/product - method: post - product: " + product);

        req.setAttribute("product", product);

        boolean isProductAddedToBucket = bucketService.add(user, product);
        log.info("/product - method: post - isAdded: " + isProductAddedToBucket);

        final RequestDispatcher requestDispatcher = isProductAddedToBucket
                ? req.getRequestDispatcher(Paths.PRODUCT_ADDED_TO_BUCKET_PATH)
                : req.getRequestDispatcher(Paths.PRODUCT_IS_NOT_ADDED_TO_BUCKET_PATH);
        requestDispatcher.forward(req, resp);
    }
}
