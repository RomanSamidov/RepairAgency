package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.LoginCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.LogoutCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.SignupCommand;

public enum PostCommandEnum{
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    SIGNUP {{
        this.command = new SignupCommand();
    }};


    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}