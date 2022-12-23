package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.LoginCommandI;
import com.myCompany.RepairAgency.servlet.request.post.realization.LogoutCommandI;
import com.myCompany.RepairAgency.servlet.request.post.realization.SignupCommandI;

public enum PostCommandEnum{
    LOGIN {{
        this.command = new LoginCommandI();
    }},
    LOGOUT {{
        this.command = new LogoutCommandI();
    }},
    SIGNUP {{
        this.command = new SignupCommandI();
    }};


    IActionCommand command;
    public IActionCommand getCurrentCommand() {
        return command;
    }
}