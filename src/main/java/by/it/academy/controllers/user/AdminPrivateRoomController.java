package by.it.academy.controllers.user;

import by.it.academy.Paths;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/adminPrivateRoom")
public class AdminPrivateRoomController extends HttpServlet {
    Logger log = Logger.getLogger(UserLogInController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.ADMIN_PRIVATE_ROOM);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        session.invalidate();
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(Paths.INDEX_PATH);
        requestDispatcher.forward(req, resp);

    }
}
