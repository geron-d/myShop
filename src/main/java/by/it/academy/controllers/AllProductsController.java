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

@WebServlet(urlPatterns = "/products")
public class AllProductsController extends HttpServlet {
    ConnectionSQL connection = new ConnectionMySQL5_1_5();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);
    private static final String ALL_PRODUCTS_PATH = "/pages/AllProducts.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(ALL_PRODUCTS_PATH);
        req.setAttribute("products", productService.readAll());
        System.out.println(productService.readAll());
        requestDispatcher.forward(req, resp);
    }
}
