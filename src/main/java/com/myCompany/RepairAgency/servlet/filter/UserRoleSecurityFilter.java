package com.myCompany.RepairAgency.servlet.filter;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.request.get.GetCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.PostCommandFactory;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

//@WebFilter(urlPatterns = { "/controller/*" },
//           servletNames = { "Controller" })
/**
 *  This filter responsible for filtering pages which user can see.
 *  When user try to look page that blocked for his role, redirect to home.
 */
@WebFilter(filterName = "UserRoleSecurityFilter",
        urlPatterns = {"/*"})
public class UserRoleSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(UserRoleSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        logger.debug(" doFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String requestURI = req.getRequestURI();
        logger.debug(" RequestURI = " + req.getRequestURI());
        if (!requestURI.startsWith("/RepairAgency/controller/")) {
            logger.debug(" RequestURI do not start with /RepairAgency/controller/ then redirect");
            redirect(req, res);
            return;
        }

        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");
        if (userRole == null) {
            userRole = Constants.ROLE.Guest;
            initiateGuest(session);
        }

        logger.debug(" userRole = " + userRole.getToString());

        boolean allowed = true;
        String method = req.getMethod();
        if (method.equals("GET")) {
            allowed = filterForGET(userRole, req, res);
        } else if (method.equals("POST")) {
            allowed = filterForPOST(userRole, req, res);
        }

        if (allowed) {
            logger.debug(" pass");
            chain.doFilter(request, response);
        }
    }

    private void initiateGuest(HttpSession session) {
        session.setAttribute("userRole", Constants.ROLE.Guest);
        session.setAttribute("_menu_url", PathFactory.getPath("path.template.menu.guest").toString());
    }

    private boolean filterForPOST(Constants.ROLE userRole, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        IActionCommand command = PostCommandFactory.getInstance().defineCommand(request);
        return checkAllowedRoles(command, userRole, request, response);
    }

    private boolean filterForGET(Constants.ROLE userRole, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        IActionCommand command = GetCommandFactory.getInstance().defineCommand(request);
        return checkAllowedRoles(command, userRole, request, response);
    }

    private boolean checkAllowedRoles(IActionCommand command, Constants.ROLE userRole,
                                      HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (command instanceof IHasRoleRequirement) {
            if (!((IHasRoleRequirement) command).allowedUserRoles().contains(userRole)) {
                redirect(request, response);
                return false;
            }
        }
        return true;
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath()
                + PathFactory.getPath("path.page.redirect.home"));
        logger.debug(" redirected to home");

    }

    @Override
    public void init(FilterConfig fConfig) {
        logger.debug(" initiated");
    }

    @Override
    public void destroy() {
        logger.debug(" destroyed");
    }
}