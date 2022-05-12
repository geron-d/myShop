package by.it.academy.controllers;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class DefaultController extends HttpServlet {
    private final ConnectionSQL connection = new ConnectionMySQL();

    protected ProductService<Product> createProductAPIService() {
        ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
        return new ProductAPIService(productAPIRepository);
    }

    protected void logUserInSession(HttpSession session, Logger log) {
        User user = (User) session.getAttribute("user");
        log.info("userInSession: " + user);
    }

    protected Product getProductByParams(HttpServletRequest req) {
        return Product.builder()
                .category(req.getParameter("category"))
                .type(req.getParameter("type"))
                .name(req.getParameter("name"))
                .image_path(req.getParameter("image"))
                .producer(req.getParameter("producer"))
                .amount(Integer.parseInt(req.getParameter("amount")))
                .price(Double.parseDouble(req.getParameter("price")))
                .build();
    }
}
