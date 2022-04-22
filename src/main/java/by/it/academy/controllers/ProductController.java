package by.it.academy.controllers;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product")
public class ProductController extends HttpServlet {
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);
    private static final String PRODUCT_PATH = "/pages/Product.jsp";
    private static final String PRODUCT_ADDED_TO_BUCKET_PATH = "/pages/ProductAddedToBucket.jsp";
    private static final String NO_PRODUCTS_PATH = "/pages/NoProducts.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getByID(id);
        req.setAttribute("product", product);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_PATH);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getByID(id);
        boolean isProductHas = productService.isProductHas(product);
        if (isProductHas) {
            product = productService.buyProduct(product);
            req.setAttribute("product", product);
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_ADDED_TO_BUCKET_PATH);
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("product", product);
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(NO_PRODUCTS_PATH);
            requestDispatcher.forward(req, resp);
        }
    }
}
