//package com.myCompany.RepairAgency.servlet.filter;
//
////import org.apache.log4j.Logger;
//
//import jakarta.servlet.*;
//
//import java.io.IOException;
//
////@WebFilter( dispatcherTypes = {
////            DispatcherType.REQUEST,
////            DispatcherType.FORWARD,
////            DispatcherType.INCLUDE
////            },
////           urlPatterns = {"/*"},
////           initParams = {
////           @WebInitParam(name = "encoding",
////                         value = "UTF-8",
////                         description = "Encoding Param") }
////           )
//
//public class EncodingFilter implements Filter {
//    //  private static final Logger log = Logger.getLogger(EncodingFilter.class);
//    private String encoding;
//
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
////    log.debug("Filter starts");
//
//        String requestEncoding = request.getCharacterEncoding();
//      if(encoding!=null&&!encoding.equalsIgnoreCase(requestEncoding)) {
//        //      log.trace("Request encoding = null, set encoding --> " + encoding);
//          System.out.println("encodingFilter");
//        request.setCharacterEncoding(encoding);
//        response.setCharacterEncoding(encoding);
//      }
////    log.debug("Filter finished");
//        System.out.println("EncodingFilter passed");
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig config) {
////    log.debug("Filter initialization starts");
//        encoding = config.getInitParameter("encoding");
//        System.out.println("EncodingFilter initiated");
////    log.trace("Encoding from web.xml --> " + encoding);
////    log.debug("Filter initialization finished");
//    }
//
//    @Override
//    public void destroy() {
////    log.debug("Filter destruction starts");
////    // do nothing
//        System.out.println("EncodingFilter destroyed");
////    log.debug("Filter destruction finished");
//    }
//}
//
//
