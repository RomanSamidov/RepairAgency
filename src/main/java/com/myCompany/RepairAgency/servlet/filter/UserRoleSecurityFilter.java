package com.myCompany.RepairAgency.servlet.filter;


import com.myCompany.RepairAgency.servlet.request.post.PostCommandEnum;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = { "/controller/*" },
//           servletNames = { "Controller" })
public class UserRoleSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String type = (String) session.getAttribute("userRole");
        String command = request.getParameter("command");
        if (type == null &&
                !(command != null && command.toUpperCase().equals(PostCommandEnum.LOGIN.name()))) {
            System.out.println("UserRoleSecurityFilter redirected to index");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
        System.out.println("UserRoleSecurityFilter pass");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("UserRoleSecurityFilter initiated");
    }

    @Override
    public void destroy() {
        System.out.println("UserRoleSecurityFilter destroyed");
    }
}