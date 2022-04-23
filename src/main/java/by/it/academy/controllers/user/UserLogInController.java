package by.it.academy.controllers.user;

import by.it.academy.Paths;
import by.it.academy.entities.AccessLevel;
import by.it.academy.entities.User;
import by.it.academy.repositories.connections.ConnectionMySQL;
import by.it.academy.repositories.connections.ConnectionSQL;
import by.it.academy.repositories.user.UserAPIRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserAPIService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/user/login")
public class UserLogInController extends HttpServlet {
    Logger log = Logger.getLogger(UserLogInController.class);
    ConnectionSQL connection = new ConnectionMySQL();
    UserRepository<User> userRepository = new UserAPIRepository(connection);
    UserService<User> userService = new UserAPIService(userRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        User user = userService.getByLoginPassword(login, password);
        log.info(user);

        if (Objects.nonNull(req.getSession()) && (user.getId() > 0)) {
            final HttpSession session = req.getSession();
            session.setAttribute("user", user);
            final RequestDispatcher requestDispatcher;
            if (user.getAccessLevel().equals(AccessLevel.USER)) {
                requestDispatcher = req.getRequestDispatcher(Paths.START_PAGE_USER_PATH);
            } else {
                requestDispatcher = req.getRequestDispatcher(Paths.START_PAGE_ADMIN_PATH);
            }
            requestDispatcher.forward(req, resp);

        } else {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.USER_LOGIN_ERROR);
            requestDispatcher.forward(req, resp);
        }

    }
}
