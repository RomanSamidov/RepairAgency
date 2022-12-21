package com.myCompany.RepairAgency.servlet;

import com.myCompany.RepairAgency.servlet.listener.Logger;
import com.myCompany.RepairAgency.servlet.request.ActionCommand;
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

import java.io.IOException;
import java.util.Arrays;



//<c:redirect  url="/controller" />
//<jsp:forward page = "/WEB_INF/jsp/login.jsp"/>

@WebServlet("/controller/*")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger.log( "[Controller] its get");
        processRequest(GetCommandFactory.inst, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger.log("[Controller] its post");
        processRequest(PostCommandFactory.inst, request, response);
    }
    private void processRequest(abstractCommandFactory factory, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logRequest(request);

        // определение команды, пришедшей из JSP
        ActionCommand command = factory.defineCommand(request);
        Path page  = command.execute(request);
        Logger.log("[Controller] " + command);
        Logger.log("[Controller] " + page);


        if(page == null || page.isBlank()) {
// установка страницы c coобщeнием об ошибке
            page = PathFactory.getPath("path.page.redirect.index");
            Logger.log("[Controller] processRequest end with error");
            response.sendRedirect(request.getContextPath() + page);
        }

        if(page.isRedirect) {
            Logger.log("[Controller] processRequest end with redirect");
            response.sendRedirect(request.getContextPath() + page);
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page.toString());
        Logger.log("[Controller] processRequest end with forward");
        dispatcher.forward(request, response);
    }


    private void logRequest(HttpServletRequest request) {
        Logger.log("[Controller] processRequest start");
        Logger.log("[Controller] request Cookies");
        Arrays.stream(request.getCookies()).forEach(x-> x
                .getAttributes()
                .forEach((key, value) -> Logger.log("[Controller] " + key + " = " + value)));

        Logger.log("[Controller] request attributes");
        request.getAttributeNames().asIterator().forEachRemaining(x -> {
            Logger.log("[Controller] " + x + " = " + request.getAttribute(x));
        });

        Logger.log("[Controller] request parameters");
        request.getParameterMap().forEach((x,y)->{
            StringBuilder par = new StringBuilder("[Controller] " + x + " = ");
            Arrays.stream(y).forEach(c -> par.append(c).append(", "));
            Logger.log(par.toString());
        });

        HttpSession sessi = request.getSession(false);
        if(sessi != null) {
            Logger.log("[Controller] request session parameters");
            sessi.getAttributeNames().asIterator().forEachRemaining(x -> {
                Logger.log("[Controller] " + x + " = " + sessi.getAttribute(x));
            });
        } else Logger.log("[Controller] request has no session");
    }
}