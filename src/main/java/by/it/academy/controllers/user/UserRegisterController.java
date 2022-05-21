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

@WebServlet(urlPatterns = "/user/create")
public class UserRegisterController extends DefaultController {
    Logger log = Logger.getLogger(UserRegisterController.class);
    UserService<User> userService = createUserAPIService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        log.info("/user/create - method: post - login: " + login);

        final String password = req.getParameter("password");
        log.info("/user/create - method: post - password: " + password);

        User user = new User(login, password);
        log.info("/user/create - method: post - user: " + user);

        boolean isUserCreated = userService.create(user);
        log.info("/user/create - method: post - isCreated: " + isUserCreated);

        final RequestDispatcher requestDispatcher;
        if (isUserCreated) {
            final HttpSession session = req.getSession();
            session.setAttribute("user", user);

            requestDispatcher = req.getRequestDispatcher("/start");
        } else {
            requestDispatcher = req.getRequestDispatcher(Paths.DATA_BASE_ERROR);
        }
        requestDispatcher.forward(req, resp);
    }
}
