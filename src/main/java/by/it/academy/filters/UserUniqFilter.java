package by.it.academy.filters;

import by.it.academy.contants.Paths;
import by.it.academy.entities.User;
import by.it.academy.repositories.hiber.user.UserAPIRepository;
import by.it.academy.repositories.hiber.user.UserRepository;
import by.it.academy.repositories.hiber.utils.HibernateUtils;
import by.it.academy.services.user.UserAPIService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter for checking uniq user login.
 *
 * @author Maxim Zhevnov
 */
@WebFilter(urlPatterns = "/user/create")
public class UserUniqFilter implements Filter {
    Logger log = Logger.getLogger(UserUniqFilter.class);
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final Session session = sessionFactory.openSession();
    private final UserRepository<User> userRepository = new UserAPIRepository(session);
    private final UserService<User> userService = new UserAPIService(session, userRepository);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = request.getParameter("login");
        log.info("/user/create - method: filter - login: " + login);

        if (userService.checkUserLogin(login)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Paths.USER_LOGIN_IS_ALREADY_TAKEN_ERROR);
            requestDispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
