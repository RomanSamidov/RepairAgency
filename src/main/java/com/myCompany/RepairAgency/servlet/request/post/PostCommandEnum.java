package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.*;

public enum PostCommandEnum {
    LOGIN {{
        this.command = new LoginCommand();
    }},
    LOGOUT {{
        this.command = new LogoutCommand();
    }},
    SIGNUP {{
        this.command = new SignupCommand();
    }},
    CREATE_USER {{
        this.command = new CreateUserCommand();
    }},
    USER_CREATE_ORDER {{
        this.command = new UserCreateOrderCommand();
    }},
    ADMIN_CREATE_ORDER {{
        this.command = new AdminCreateOrderCommand();
    }},
    ORDERS {{
        this.command = new OrdersCommand();
    }},
    SET_ORDER_STATUS {{
        this.command = new SetOrderStatusCommand();
    }},
    SET_FEEDBACK_FOR_ORDER {{
        this.command = new SetFeedbackForOrderCommand();
    }},
    USERS {{
        this.command = new UsersCommand();
    }},
    ADD_TO_USER_ACCOUNT {{
        this.command = new AddToUserAccountCommand();
    }},
    DELETE_USER {{
        this.command = new DeleteUserCommand();
    }},
    DELETE_ORDER {{
        this.command = new DeleteOrderCommand();
    }},
    CONFIRM_EMAIL {{
        this.command = new ConfirmEmailCommand();
    }},
    CHANGE_EMAIL {{
        this.command = new ChangeEmailCommand();
    }},
    CLIENT_PAY_FOR_ORDER {{
        this.command = new ClientPayForOrderCommand();
    }},
    MANAGER_PAY_FOR_ORDER {{
        this.command = new ManagerPayForOrderCommand();
    }},
    CANCEL_ORDER {{
        this.command = new CancelOrderCommand();
    }},
    SET_CRAFTSMAN_AND_PRICE {{
        this.command = new SetCraftsmanAndPriceCommand();
    }},
    CHANGE_PASSWORD {{
        this.command = new ChangePasswordCommand();
    }},
    CHANGE_PROFILE_SETTINGS {{
        this.command = new ChangeProfileSettingsCommand();
    }};


    IActionCommand command;

    public IActionCommand getCurrentCommand() {
        return command;
    }
}