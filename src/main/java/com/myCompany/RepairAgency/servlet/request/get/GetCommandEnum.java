package com.myCompany.RepairAgency.servlet.request.get;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.get.realization.*;

public enum GetCommandEnum {
    LOGIN(new ShowLoginFormCommand()),
    SIGNUP(new ShowSignupFormCommand()),
    CREATE_USER(new ShowCreateUserFormCommand()),
    ABOUT(new ShowAboutCommand()),
    FAQS(new ShowFAQsCommand()),
    HOME(new ShowHomeCommand()),
    PRICING(new ShowPricingCommand()),
    CABINET(new ShowProfileCommand()),
    ORDER(new ShowOrderPageCommand()),
    ORDERS(new ShowOrdersCommand()),
    USERS(new ShowUsersCommand()),
    USER(new ShowUserCommand()),
    DOWNLOAD(new DownloadFileCommand()),
    CHANGE_PASSWORD(new ShowChangePasswordCommand());

    private final IActionCommand command;

    GetCommandEnum(IActionCommand command) {
        this.command = command;
    }

    public IActionCommand getCurrentCommand() {
        return command;
    }
}