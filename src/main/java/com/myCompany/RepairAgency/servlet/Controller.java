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



@WebServlet("/controller/*")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Its get");
        processRequest(GetCommandFactory.inst, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Its post");
        processRequest(PostCommandFactory.inst, request, response);
    }

    private void processRequest(abstractCommandFactory factory, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logRequest(request);

        // определение команды, пришедшей из JSP
        IActionCommand command = factory.defineCommand(request);
        Path page = command.execute(request, response);
        logger.debug("Command " + command);
        logger.debug("Page " + page);

        if (page != null && page.isDownload()) {
            logger.debug("ProcessRequest end with download");
            return;
        }

        if (page == null || page.isBlank()) {
            page = PathFactory.getPath("path.page.redirect.index");
            logger.debug("ProcessRequest end with error");
        }

        if (page.isRedirect) {
            logger.debug("ProcessRequest end with redirect");
            response.sendRedirect(request.getContextPath() + page);
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page.toString());
        logger.debug("ProcessRequest end with forward");
        dispatcher.forward(request, response);
    }


    private void logRequest(HttpServletRequest request) {
        logger.debug("ProcessRequest start");
        logger.debug("Request Cookies");
        Arrays.stream(request.getCookies()).forEach(x -> x
                .getAttributes()
                .forEach((key, value) -> logger.debug(" " + key + " = " + value)));

        logger.debug("Request attributes");
        request.getAttributeNames().asIterator().forEachRemaining(x -> logger
                .debug(" " + x + " = " + request.getAttribute(x)));

        logger.debug("Request parameters");
        request.getParameterMap().forEach((x, y) -> {
            StringBuilder par = new StringBuilder(" " + x + " = ");
            Arrays.stream(y).forEach(c -> par.append(c).append(", "));
            logger.debug(par.toString());
        });

        HttpSession session  = request.getSession(false);
        if (session != null) {
            logger.debug("Request session parameters");
            session.getAttributeNames().asIterator().forEachRemaining(x -> logger
                    .debug(" " + x + " = " + session.getAttribute(x)));
        } else logger.debug("Request has no session");
    }
}