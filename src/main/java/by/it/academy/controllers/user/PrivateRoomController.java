package by.it.academy.controllers.user;

import by.it.academy.Paths;
import by.it.academy.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/userPrivateRoom")
public class PrivateRoomController extends HttpServlet {
    Logger log = Logger.getLogger(PrivateRoomController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.USER_PRIVATE_ROOM);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        log.info("/user/userPrivateRoom - method: post - user: " + user);

        session.invalidate();
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.INDEX_PATH);
        requestDispatcher.forward(req, resp);

    }
}
