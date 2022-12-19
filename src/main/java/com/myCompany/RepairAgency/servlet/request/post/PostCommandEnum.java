package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.*;

public enum PostCommandEnum{
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    SIGNUP {{
        this.command = new SignupCommand();
    }},
    SHOWPROFILE {{
        this.command = new ShowProfileCommand();
    }};


    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}