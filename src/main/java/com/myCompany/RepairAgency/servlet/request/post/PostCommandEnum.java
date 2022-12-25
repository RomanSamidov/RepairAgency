package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
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
    CREATE_ORDER {{
        this.command = new CreateOrderCommand();
    }},
    MY_ORDERS {{
        this.command = new MyOrdersCommand();
    }},
    USERS {{
        this.command = new UsersCommand();
    }},
    USER {{
        this.command = new UserCommand();
    }};


    IActionCommand command;
    public IActionCommand getCurrentCommand() {
        return command;
    }
}