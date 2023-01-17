package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.post.realization.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PostCommandFactory extends abstractCommandFactory {

    public static final PostCommandFactory inst = new PostCommandFactory();
    private static final Logger logger = LogManager.getLogger(PostCommandFactory.class);

    private PostCommandFactory() {
    }

    public IActionCommand defineCommand(HttpServletRequest request) {
        IActionCommand current = new EmptyCommand();

        String command = request.getParameter("command");
        logger.debug(" " + command);
        if (command == null || command.isEmpty()) {
            return current;
        }

        try {
            PostCommandEnum currentEnum = PostCommandEnum.valueOf(command.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.debug("Wrong command");
            request.setAttribute("wrongCommand",
                    command + "message.wrongCommand");
        }
        return current;
    }
}