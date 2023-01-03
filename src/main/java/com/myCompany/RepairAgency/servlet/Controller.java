package com.myCompany.RepairAgency.servlet;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.get.GetCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.PostCommandFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;


//<c:redirect  url="/controller" />
//<jsp:forward page = "/WEB_INF/jsp/login.jsp"/>

@WebServlet("/controller/*")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("[Controller] its get");
        processRequest(GetCommandFactory.inst, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("[Controller] its post");
        processRequest(PostCommandFactory.inst, request, response);
    }

    private void processRequest(abstractCommandFactory factory, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logRequest(request);

        // определение команды, пришедшей из JSP
        IActionCommand command = factory.defineCommand(request);
        Path page = command.execute(request, response);
        logger.debug("[Controller] " + command);
        logger.debug("[Controller] " + page);


        if (page == null || page.isBlank()) {
// установка страницы c coобщeнием об ошибке
            page = PathFactory.getPath("path.page.redirect.index");
            logger.debug("[Controller] processRequest end with error");
        }

        if (page.isRedirect) {
            logger.debug("[Controller] processRequest end with redirect");
            response.sendRedirect(request.getContextPath() + page);
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page.toString());
        logger.debug("[Controller] processRequest end with forward");
        dispatcher.forward(request, response);
    }


    private void logRequest(HttpServletRequest request) {
        logger.debug("[Controller] processRequest start");
        logger.debug("[Controller] request Cookies");
        Arrays.stream(request.getCookies()).forEach(x -> x
                .getAttributes()
                .forEach((key, value) -> logger.debug("[Controller] " + key + " = " + value)));

        logger.debug("[Controller] request attributes");
        request.getAttributeNames().asIterator().forEachRemaining(x -> logger
                .debug("[Controller] " + x + " = " + request.getAttribute(x)));

        logger.debug("[Controller] request parameters");
        request.getParameterMap().forEach((x, y) -> {
            StringBuilder par = new StringBuilder("[Controller] " + x + " = ");
            Arrays.stream(y).forEach(c -> par.append(c).append(", "));
            logger.debug(par.toString());
        });

        HttpSession sessi = request.getSession(false);
        if (sessi != null) {
            logger.debug("[Controller] request session parameters");
            sessi.getAttributeNames().asIterator().forEachRemaining(x -> logger
                    .debug("[Controller] " + x + " = " + sessi.getAttribute(x)));
        } else logger.debug("[Controller] request has no session");
    }
}