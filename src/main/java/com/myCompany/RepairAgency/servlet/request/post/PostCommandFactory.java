package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.realization.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;


public class PostCommandFactory extends abstractCommandFactory {

    public static final PostCommandFactory inst = new PostCommandFactory();

    private PostCommandFactory() {}

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String command = request.getParameter("command");
        System.out.println(command + "  comFactory");
        if (command == null || command.isEmpty()) {
             return current;
        }

        try {
            PostCommandEnum currentEnum = PostCommandEnum.valueOf(command.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongCommand",
                    command + "message.wrongCommand");
        }
        return current;
    }
}