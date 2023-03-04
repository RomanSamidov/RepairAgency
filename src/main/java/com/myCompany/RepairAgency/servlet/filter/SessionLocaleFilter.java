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

/**
 *  This filter responsible for user's language selection,
 *  save/read language from cookies,
 *  when need save language to DB.
 */
@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(SessionLocaleFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.debug("Filter starts");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (req.getParameter("language") != null) {
            changeLanguage(res, req, session);
            res.sendRedirect(req.getRequestURI());
            return;
        } else
        if (session.getAttribute("language") == null) {
            session.setAttribute("language", readLanguageFromCookies(req));
            logger.debug("language was null set to " + session.getAttribute("language"));
        }

        logger.debug("Filter finished");
        chain.doFilter(request, response);
    }


    private String readLanguageFromCookies(HttpServletRequest req){
        return Stream.ofNullable(req.getCookies())
                .flatMap(Arrays::stream)
                .filter(cookie -> cookie.getName().equals("language"))
                .map(Cookie::getValue)
                .findAny().orElse("en_US");
    }


    private void changeLanguage(HttpServletResponse res, HttpServletRequest req, HttpSession session)
    {
        if (session.getAttribute("userId") != null) {
            saveLanguageInDB(req, session);
        }

        logger.debug("language change from " + session.getAttribute("language")
                + " to " + req.getParameter("language"));
        session.setAttribute("language", req.getParameter("language"));

        saveLanguageInCookie(req, res);
    }

    private void saveLanguageInCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie languageCookie = new Cookie("language", req.getParameter("language"));
        languageCookie.setMaxAge(Integer.MAX_VALUE);
        res.addCookie(languageCookie);
    }

    private void saveLanguageInDB(HttpServletRequest req, HttpSession session) {
        User user = UserService.get((Long) session.getAttribute("userId"));
        user.setLocale_id(Constants.LOCALE.valueOf(req.getParameter("language")).ordinal());
        UserService.update(user);
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

