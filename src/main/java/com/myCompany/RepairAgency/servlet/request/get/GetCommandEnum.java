package com.myCompany.RepairAgency.servlet.request.get;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.get.realization.*;

public enum GetCommandEnum {
    LOGIN {{
        this.command = new ShowLoginFormCommand();
    }},
    SIGNUP {{
        this.command = new ShowSignupFormCommand();
    }},
    CREATE_USER {{
        this.command = new ShowCreateUserFormCommand();
    }},
    ABOUT {{
        this.command = new ShowAboutCommand();
    }},
    FAQS {{
        this.command = new ShowFAQsCommand();
    }},
    HOME {{
        this.command = new ShowHomeCommand();
    }},
    PRICING {{
        this.command = new ShowPricingCommand();
    }},
    CABINET {{
        this.command = new ShowProfileCommand();
    }},
    ORDER {{
        this.command = new ShowOrderPageCommand();
    }},
    ORDERS {{
        this.command = new ShowOrdersCommand();
    }},
    USERS {{
        this.command = new ShowUsersCommand();
    }},
    USER {{
        this.command = new ShowUserCommand();
    }},
    DOWNLOAD {{
        this.command = new DownloadFileCommand();
    }},
    CHANGE_PASSWORD {{
        this.command = new ShowChangePasswordCommand();
    }};


    IActionCommand command;

    public IActionCommand getCurrentCommand() {
        return command;
    }
}