package com.myCompany.RepairAgency.servlet.request.get;


import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.get.realization.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;


public class GetCommandFactory extends abstractCommandFactory {

    public static final GetCommandFactory inst = new GetCommandFactory();

    private GetCommandFactory() {}

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

//        String command = request.getParameter("command");
        String command = request.getRequestURI();
        command = command.substring(command.indexOf("/controller/")+12);
        System.out.println(command + "   Get  comFactory");
        if (command == null || command.isEmpty()) {
             return current;
        }

        try {
            GetCommandEnum currentEnum = GetCommandEnum.valueOf(command.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongCommand",
                    command + "message.wrongCommand");
        }
        return current;
    }
}