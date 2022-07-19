package by.it.academy.controllers;

import by.it.academy.entities.*;
import by.it.academy.repositories.hiber.category.CategoryAPIRepository;
import by.it.academy.repositories.hiber.category.CategoryRepository;
import by.it.academy.repositories.hiber.producer.ProducerAPIRepository;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import by.it.academy.repositories.hiber.product.ProductAPIRepository;
import by.it.academy.repositories.hiber.product.ProductRepository;
import by.it.academy.repositories.hiber.productInBucket.ProductInBucketAPIRepository;
import by.it.academy.repositories.hiber.productInBucket.ProductInBucketRepository;
import by.it.academy.repositories.hiber.type.TypeAPIRepository;
import by.it.academy.repositories.hiber.type.TypeRepository;
import by.it.academy.repositories.hiber.user.UserAPIRepository;
import by.it.academy.repositories.hiber.user.UserRepository;
import by.it.academy.repositories.hiber.utils.HibernateUtils;
import by.it.academy.services.category.CategoryAPIService;
import by.it.academy.services.category.CategoryService;
import by.it.academy.services.producer.ProducerAPIService;
import by.it.academy.services.producer.ProducerService;
import by.it.academy.services.product.ProductAPIService;
import by.it.academy.services.product.ProductService;
import by.it.academy.services.productInBucket.ProductInBucketAPIService;
import by.it.academy.services.productInBucket.ProductInBucketService;
import by.it.academy.services.type.TypeAPIService;
import by.it.academy.services.type.TypeService;
import by.it.academy.services.user.UserAPIService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * Default controller which realized creating session, repositories, services and getting product by param
 *
 * @author Maxim Zhevnov
 */
public class DefaultController extends HttpServlet {
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final Session session = sessionFactory.openSession();

    protected CategoryService<Category> createCategoryAPIService() {
        final CategoryRepository<Category> categoryRepository = new CategoryAPIRepository(session);
        return new CategoryAPIService(session, categoryRepository);
    }

    protected TypeService<Type> createTypeAPIService() {
        final TypeRepository<Type> typeRepository = new TypeAPIRepository(session);
        return new TypeAPIService(session, typeRepository);
    }

    protected ProducerService<Producer> createProducerAPIService() {
        final ProducerRepository<Producer> producerRepository = new ProducerAPIRepository(session);
        return new ProducerAPIService(session, producerRepository);
    }

    protected UserService<User> createUserAPIService() {
        final UserRepository<User> userRepository = new UserAPIRepository(session);
        return new UserAPIService(session, userRepository);
    }

    protected ProductService<Product> createProductAPIService() {
        final ProductRepository<Product> productAPIRepository = new ProductAPIRepository(session,
                new CategoryAPIRepository(session), new TypeAPIRepository(session),
                new ProducerAPIRepository(session));
        return new ProductAPIService(session, productAPIRepository);
    }

    protected ProductInBucketService<ProductInBucket> createProductInBucketAPIService() {
        final ProductInBucketRepository<ProductInBucket> productInBucketRepository =
                new ProductInBucketAPIRepository(session);
        return new ProductInBucketAPIService(session, productInBucketRepository, createProductAPIService());
    }

    protected void logUserInSession(HttpSession session, Logger log) {
        User user = (User) session.getAttribute("user");
        log.info("userInSession: " + user);
    }

        protected Product getProductByParams(HttpServletRequest req, CategoryService<Category> categoryService,
                                             TypeService<Type> typeService, ProducerService<Producer> producerService) {
                return Product.builder()
                        .category(categoryService.getCategoryByName(req.getParameter("category")).get())
                        .type(typeService.getTypeByName(req.getParameter("type")).get())
                        .name(req.getParameter("name"))
                        .imagePath(req.getParameter("image"))
                        .dateInserting(LocalDate.now())
                        .producer(producerService.getProducerByName(req.getParameter("producer")).get())
                        .amount(Integer.parseInt(req.getParameter("amount")))
                        .price(Double.parseDouble(req.getParameter("price")))
                        .build();
        }

}
