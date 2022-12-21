package com.myCompany.RepairAgency.servlet.filter;


import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.listener.Logger;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = { "/controller/*" },
//           servletNames = { "Controller" })
 @WebFilter(urlPatterns = { "/*" })
public class UserRoleSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Logger.log("[UserRoleSecurityFilter] doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");
        String requestURI = req.getRequestURI();
        Logger.log("[UserRoleSecurityFilter] "+ req.getRequestURI());
        if(userRole == null ) {
            Logger.log("[UserRoleSecurityFilter] null");
        } else {
            Logger.log("[UserRoleSecurityFilter] " + userRole.name());
        }

        if(!requestURI.startsWith("/RepairAgency/controller/")) {
            redirect(req,resp);
            return;
        }

        if(userRole == null) {
            if(!isPathForGuest(requestURI)) {
                redirect(req, resp);
                return;
            }
        } else if(userRole.equals(Constants.ROLE.Client)) {
            if(!isPathForClient(requestURI)) {
                redirect(req, resp);
                return;
            }
        } else if(userRole.equals(Constants.ROLE.Craftsman)) {
            if(!isPathForCraftsman(requestURI)) {
                redirect(req, resp);
                return;
            }
        } else if(userRole.equals(Constants.ROLE.Manager)) {
            if(!isPathForManager(requestURI)) {
                redirect(req, resp);
                return;
            }
        } else if(userRole.equals(Constants.ROLE.Admin)) {
            if(!isPathForAdmin(requestURI)) {
                redirect(req, resp);
                return;
            }
        }

       Logger.log("[UserRoleSecurityFilter] pass");
        chain.doFilter(request, response);
    }

    private boolean isPathForGuest(String requestURI) {
        switch (requestURI) {
            case "/RepairAgency/controller/home":
            case "/RepairAgency/controller/pricing":
            case "/RepairAgency/controller/faqs":
            case "/RepairAgency/controller/about":
            case "/RepairAgency/controller/login":
            case "/RepairAgency/controller/signup":
                return true;
            default:
                return false;
        }
    }
    private boolean isPathForClient(String requestURI) {
        switch (requestURI) {
            case "/RepairAgency/controller/login":
            case "/RepairAgency/controller/signup":
                return false;
            case "/RepairAgency/controller/cabinet":
                return true;
            default:
                return isPathForGuest(requestURI);
        }
    }

    private boolean isPathForCraftsman(String requestURI) {
        switch (requestURI) {
            case "/RepairAgency/controller/cabinet":
                return true;
            default:
                return isPathForClient(requestURI);
        }
    }

    private boolean isPathForManager(String requestURI) {
        switch (requestURI) {
            case "/RepairAgency/controller/cabinet":
                return true;
            default:
                return isPathForClient(requestURI);
        }
    }
    private boolean isPathForAdmin(String requestURI) {
        return true;
    }


    private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath()
                + PathFactory.getPath("path.page.redirect.home"));
       Logger.log("[UserRoleSecurityFilter] redirected to home");

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
       Logger.log("[UserRoleSecurityFilter] initiated");
    }

    @Override
    public void destroy() {
       Logger.log("[UserRoleSecurityFilter] destroyed");
    }
}