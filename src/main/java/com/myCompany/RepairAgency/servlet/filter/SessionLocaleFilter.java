package com.myCompany.RepairAgency.servlet.filter;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(SessionLocaleFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

            throws IOException, ServletException {
        logger.debug("Filter starts");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();


        if (req.getParameter("language") != null) {
            if (session.getAttribute("userId") != null) {
                User user = UserService.get((Long) session.getAttribute("userId"));
                user.setLocale_id(Constants.LOCALE.valueOf(req.getParameter("language")).ordinal());
                UserService.update(user);
            }

            logger.debug("language changed from " + session.getAttribute("language")
                    + " to " + req.getParameter("language"));
            session.setAttribute("language", req.getParameter("language"));
            Cookie languageCookie = new Cookie("language", req.getParameter("language"));
            languageCookie.setMaxAge(Integer.MAX_VALUE);
            ((HttpServletResponse) response).addCookie(languageCookie);
            ((HttpServletResponse)response).sendRedirect(req.getRequestURI());
            return;
        }

        if (session.getAttribute("language") == null) {
            session.setAttribute("language",
                    Stream.ofNullable(req.getCookies())
                            .flatMap(Arrays::stream)
                            .filter(cookie -> cookie.getName().equals("language"))
                            .map(Cookie::getValue)
                            .findAny().orElse("en_US"));
            logger.debug("language was null set to en_US");

        }

        logger.debug("Filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
        logger.debug("Filter initialization");
    }

    @Override
    public void destroy() {
        logger.debug("Filter destruction");
    }
}

