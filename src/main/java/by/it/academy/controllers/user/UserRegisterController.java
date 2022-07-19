package by.it.academy.controllers.user;

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

        userService.saveUser(user);
        log.info("/user/create - method: post - saveUser: " + user);


        final HttpSession session = req.getSession();
        session.setAttribute("user", user);

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher("/start");
        requestDispatcher.forward(req, resp);
    }
}
