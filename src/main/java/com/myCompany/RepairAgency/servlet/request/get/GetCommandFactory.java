package com.myCompany.RepairAgency.servlet.request.get;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.abstractCommandFactory;
import com.myCompany.RepairAgency.servlet.request.get.realization.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetCommandFactory extends abstractCommandFactory {

    private static final GetCommandFactory instance = new GetCommandFactory();
    private static final Logger logger = LogManager.getLogger(GetCommandFactory.class);

    private GetCommandFactory() {
    }

    public static abstractCommandFactory getInstance() {
        return instance;
    }

    public IActionCommand defineCommand(HttpServletRequest request) {
        IActionCommand current = new EmptyCommand();

        String command = request.getRequestURI();
//        if(!command.contains("/controller/")) command = (String) request.getAttribute("jakarta.servlet.include.request_uri");
        command = command.substring(command.indexOf("/controller/") + 12);
        logger.debug(command);
        if (command.isBlank()) {
            return current;
        }

        try {
            GetCommandEnum currentEnum = GetCommandEnum.valueOf(command.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.debug("Wrong command");
            request.setAttribute("wrongCommand",
                    command.toUpperCase() + " message.wrongCommand");
        }
        return current;
    }
}