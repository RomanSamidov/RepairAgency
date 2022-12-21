//package com.myCompany.RepairAgency.servlet.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
////@WebFilter( urlPatterns = { "/WEB_INF/*" },
////            initParams = { @WebInitParam(name = "INDEX_PATH",
////                                         value ="/index.jsp") })
//public class PageRedirectSecurityFilter implements Filter {
//    private String indexPath;
//
//    @Override
//    public void init(FilterConfig config) throws ServletException {
//        indexPath = config.getInitParameter("INDEX_PATH");
//        System.out.println("PageRedirectSecurityFilter initiated");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//            // переход на заданную страницу
//        System.out.println(((HttpServletRequest) request).getRequestURL());
//        System.out.println("PageRedirectSecurityFilter redirected to index");
//        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
////        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("PageRedirectSecurityFilter destroyed");
//    }
//}