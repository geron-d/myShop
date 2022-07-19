package by.it.academy.controllers.user;

import by.it.academy.contants.Paths;
import by.it.academy.controllers.DefaultController;
import by.it.academy.entities.User;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller do login user.
 *
 * @author Maxim Zhevnov
 */
@WebServlet(urlPatterns = "/user/login")
public class UserLogInController extends DefaultController {
    Logger log = Logger.getLogger(UserLogInController.class);
    UserService<User> userService = createUserAPIService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        log.info("/user/login - method: get - login: " + login);

        final String password = req.getParameter("password");
        log.info("/user/login - method: get - password: " + password);

        User user = userService.getUserByLoginPassword(login, password).get();
        log.info("/user/login - method: get - user: " + user);

        final RequestDispatcher requestDispatcher;
        if (Objects.nonNull(req.getSession()) && userService.checkUserId(user)) {
            final HttpSession session = req.getSession();
            session.setAttribute("user", user);

            requestDispatcher = req.getRequestDispatcher("/start");
        } else {
            requestDispatcher = req.getRequestDispatcher(Paths.USER_LOGIN_ERROR);
        }
        requestDispatcher.forward(req, resp);
    }
}
