package by.it.academy.filters;

import by.it.academy.contants.Paths;
import by.it.academy.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Filter for checking user authorization when user do actions needed authorization.
 *
 * @author Maxim Zhevnov
 */
@WebFilter(urlPatterns = {"/bucket", "/product/addInBucket"})
public class AuthorizationFilter implements Filter {
    Logger log = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        log.info("/bucket, /product/addInBucket - method: filter - user: " + user);

        if (Objects.isNull(user)) {
            RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(Paths.USER_NOT_LOGGED_PATH);
            requestDispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
