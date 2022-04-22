package by.it.academy.filters;

import by.it.academy.entities.AccessLevel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Objects;
//
//@WebFilter(urlPatterns = "/user/login")
//public class AuthorizationFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        final HttpSession session = httpServletRequest.getSession();
//
//        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("accessLevel"))) {
//            final AccessLevel accessLevel = (AccessLevel) session.getAttribute("accessLevel");
//
//            if (accessLevel == AccessLevel.ADMIN) {
//                chain.doFilter(request, response);
//            } else {
//                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/pages/errors/authorization_error.jsp");
//                requestDispatcher.forward(request, response);
//            }
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//}
