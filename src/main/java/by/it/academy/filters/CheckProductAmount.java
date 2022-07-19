package by.it.academy.filters;

import by.it.academy.contants.Paths;
import by.it.academy.entities.Category;
import by.it.academy.entities.Producer;
import by.it.academy.entities.Product;
import by.it.academy.entities.Type;
import by.it.academy.repositories.hiber.category.CategoryAPIRepository;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import by.it.academy.repositories.hiber.producer.ProducerAPIRepository;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import by.it.academy.repositories.hiber.product.ProductAPIRepository;
import by.it.academy.repositories.hiber.product.ProductRepository;
import by.it.academy.repositories.hiber.type.TypeAPIRepository;
import by.it.academy.repositories.hiber.type.TypeRepository;
import by.it.academy.repositories.hiber.utils.HibernateUtils;
import by.it.academy.services.category.CategoryAPIService;
import by.it.academy.services.category.CategoryService;
import by.it.academy.services.producer.ProducerAPIService;
import by.it.academy.services.producer.ProducerService;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.type.TypeAPIService;
import by.it.academy.services.type.TypeService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/product")
public class CheckProductAmount implements Filter {
    Logger log = Logger.getLogger(CheckProductAmount.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final Session session = sessionFactory.openSession();
    private final CategoryRepository<Category> categoryRepository = new CategoryAPIRepository(session);
    CategoryService<Category> categoryService = new CategoryAPIService(session, categoryRepository);
    ProducerRepository<Producer> producerRepository = new ProducerAPIRepository(session);
    ProducerService<Producer> producerPService = new ProducerAPIService(session, producerRepository);
    TypeRepository<Type> typeRepository = new TypeAPIRepository(session);
    TypeService<Type> typeService = new TypeAPIService(session, typeRepository);
    ProductRepository<Product> productRepository = new ProductAPIRepository(session, categoryRepository, typeRepository,
            producerRepository);
    ProductService<Product> productService = new ProductAPIService(session, productRepository);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        log.info("/product - method: filter - id: " + id);

        Optional<Product> product = productService.getProductById(id);
        log.info("/product - method: filter - product: " + product);

        boolean isProductGetAmount = false;

        if (product.isPresent()) {
            request.setAttribute("product", product.get());

            isProductGetAmount = productService.checkProductAmount(product.get());
            log.info("/product - method: filter - isProductGetAmount: " + isProductGetAmount);
        }

        if (isProductGetAmount) {
            chain.doFilter(request, response);
        } else {
            final RequestDispatcher requestDispatcher = request.getRequestDispatcher(Paths.NO_PRODUCTS_PATH);
            requestDispatcher.forward(request, response);
        }
    }
}
