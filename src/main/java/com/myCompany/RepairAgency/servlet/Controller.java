package com.myCompany.RepairAgency.servlet;

import com.myCompany.RepairAgency.Constants;
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
        System.out.println("its get");
        processRequest(GetCommandFactory.inst, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("its post");
        processRequest(PostCommandFactory.inst, request, response);
    }
    private void processRequest(abstractCommandFactory factory, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//////////////////////////////////////////////////////////////////////////
        System.out.println("request Cookies  con");
        Arrays.stream(request.getCookies()).forEach(x-> x
                .getAttributes()
                .entrySet()
                .forEach(System.out::println));

        System.out.println("request attributes con");
        request.getAttributeNames().asIterator().forEachRemaining(x -> {
            System.out.print(x + " = ");
            System.out.println(request.getAttribute(x));
        });

        System.out.println("request parameters con");
        request.getParameterMap().forEach((x,y)->{
            System.out.print(x + " = ");
            Arrays.stream(y).forEach(System.out::println);
        });

        HttpSession sessi = request.getSession(false);
        if(sessi != null) {
            System.out.println("request session parameters con");
            sessi.getAttributeNames().asIterator().forEachRemaining(x -> {
                System.out.print(x + " = ");
                System.out.println(sessi.getAttribute(x));
            });
        } else System.out.println("request has no session");
//////////////////////////////////////////////////////////////////////////

        // определение команды, пришедшей из JSP
        ActionCommand command = factory.defineCommand(request);
        String page  = command.execute(request);
        System.out.println(command + "   con");
        System.out.println(page + "   con");


//         page = null; // поэксперементировать!

        if(page == null || page.isBlank()) {
// установка страницы c coобщeнием об ошибке
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
            response.sendRedirect(request.getContextPath() + page);
        }

        if(page.startsWith(Constants.REDIRECT)) {

            response.sendRedirect(request.getContextPath() + page.substring(11));
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        System.out.println("////////////////////////");
        dispatcher.forward(request, response);
    }
}