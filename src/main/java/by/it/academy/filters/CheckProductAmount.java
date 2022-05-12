package by.it.academy.filters;

import by.it.academy.contants.Paths;
import by.it.academy.entities.Product;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.product.ProductAPIRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/product")
public class CheckProductAmount implements Filter {
    Logger log = Logger.getLogger(CheckProductAmount.class);
    ConnectionSQL connection = new ConnectionMySQL();
    ProductRepository<Product> productAPIRepository = new ProductAPIRepository(connection);
    ProductService<Product> productService = new ProductAPIService(productAPIRepository);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        log.info("/product - method: filter - id: " + id);

        Product product = productService.getByID(id);
        log.info("/product - method: filter - product: " + product);

        request.setAttribute("product", product);

        boolean isProductGetAmount = productService.checkProductAmount(product);
        log.info("/product - method: filter - isProductGetAmount: " + isProductGetAmount);

        if (isProductGetAmount) {
            chain.doFilter(request, response);
        } else {
            final RequestDispatcher requestDispatcher = request.getRequestDispatcher(Paths.NO_PRODUCTS_PATH);
            requestDispatcher.forward(request, response);
        }
    }
}
