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
@WebFilter(urlPatterns = {"/*"})
public class UserRoleSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(UserRoleSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        logger.debug("[UserRoleSecurityFilter] doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");
        String requestURI = req.getRequestURI();
        logger.debug("[UserRoleSecurityFilter] RequestURI = " + req.getRequestURI());
        if (userRole == null) {
            session.setAttribute("userRole", Constants.ROLE.Guest);
            userRole = (Constants.ROLE) session.getAttribute("userRole");
        }

        logger.debug("[UserRoleSecurityFilter] userRole = " + userRole.name());


        if (!requestURI.startsWith("/RepairAgency/controller/")) {
            redirect(req, resp);
            return;
        }

        boolean allowed = true;
        String method = req.getMethod();
        if (method.equals("GET")) {
            allowed = filterForGET(userRole, req, resp);
        } else if (method.equals("POST")) {
            allowed = filterForPOST(userRole, req, resp);
        }

        if (allowed) {
            logger.debug("[UserRoleSecurityFilter] pass");
            chain.doFilter(request, response);
        }
    }

    private boolean filterForPOST(Constants.ROLE userRole, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        IActionCommand command = PostCommandFactory.inst.defineCommand(request);
        return checkAlloverRoles(command, userRole, request, response);
    }

    private boolean filterForGET(Constants.ROLE userRole, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        IActionCommand command = GetCommandFactory.inst.defineCommand(request);
        return checkAlloverRoles(command, userRole, request, response);
    }

    private boolean checkAlloverRoles(IActionCommand command, Constants.ROLE userRole,
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
        logger.debug("[UserRoleSecurityFilter] redirected to home");

    }

    @Override
    public void init(FilterConfig fConfig) {
        logger.debug("[UserRoleSecurityFilter] initiated");
    }

    @Override
    public void destroy() {
        logger.debug("[UserRoleSecurityFilter] destroyed");
    }
}