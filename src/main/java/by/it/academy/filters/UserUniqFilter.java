package by.it.academy.filters;

import by.it.academy.Paths;
import by.it.academy.controllers.user.UserLogInController;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.user.UserAPIRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserAPIService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebFilter(urlPatterns = "/user/create")
public class UserUniqFilter implements Filter {
    Logger log = Logger.getLogger(UserLogInController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    UserRepository<User> userRepository = new UserAPIRepository(connection);
    UserService<User> userService = new UserAPIService(userRepository);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String login = request.getParameter("login");

        if (userService.checkLogin(login)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Paths.USER_LOGIN_IS_ALREADY_TAKEN_ERROR);
            requestDispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}