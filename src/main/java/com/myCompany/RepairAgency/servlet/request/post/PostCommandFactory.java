package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.realization.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PostCommandFactory extends abstractCommandFactory {

    private static final Logger logger = LogManager.getLogger(PostCommandFactory.class);

    public static final PostCommandFactory inst = new PostCommandFactory();

    private PostCommandFactory() {}

    public IActionCommand defineCommand(HttpServletRequest request) {
        IActionCommand current = new EmptyCommand();

        String command = request.getParameter("command");
        logger.debug("[PostCommandFactory] " + command);
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