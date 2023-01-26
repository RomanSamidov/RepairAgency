package com.myCompany.RepairAgency.servlet.filter;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        if (req.getParameter("language") != null) {
            if (((HttpServletRequest) request).getSession().getAttribute("userId") != null) {
                User user = ModelManager.getInstance().getUserRepository().getById((Long) (
                        (HttpServletRequest) request).getSession().getAttribute("userId"));
                user.setLocale_id(Constants.LOCALE.valueOf(req.getParameter("language")).ordinal());
                ModelManager.getInstance().getUserRepository().update(user);
            }

            logger.debug("language changed from " + req.getSession().getAttribute("language")
                    + " to " + req.getParameter("language"));
            req.getSession().setAttribute("language", req.getParameter("language"));
            Cookie languageCookie = new Cookie("language", req.getParameter("language"));
            languageCookie.setMaxAge(Integer.MAX_VALUE);
            ((HttpServletResponse) response).addCookie(languageCookie);
        }

        if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language",
                    Stream.ofNullable(((HttpServletRequest) request).getCookies())
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

