package by.it.academy.controllers;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connections.ConnectionMySQL5_1_5;
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
import java.time.LocalDate;

@WebServlet(urlPatterns = "/addProduct")
public class AddProductController extends HttpServlet {
    ConnectionSQL connection = new ConnectionMySQL5_1_5();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);
    private static final String ADD_PRODUCT_PATH = "/pages/AddProduct.jsp";
    private static final String PRODUCT_ADDED_PATH = "/pages/ProductAdded.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(ADD_PRODUCT_PATH);
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

        boolean isCreated = productService.create(product);
        if (isCreated) {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_ADDED_PATH);
            requestDispatcher.forward(req, resp);
        }
    }
}
