package com.myCompany.RepairAgency.servlet.request.post;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.post.realization.*;

public enum PostCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new SignupCommand()),
    CREATE_USER(new CreateUserCommand()),
    USER_CREATE_ORDER(new UserCreateOrderCommand()),
    ADMIN_CREATE_ORDER(new AdminCreateOrderCommand()),
    ORDERS(new OrdersCommand()),
    CREATE_REPORT(new CreateReportCommand()),
    SET_ORDER_STATUS(new SetOrderStatusCommand()),
    SET_FEEDBACK_FOR_ORDER(new SetFeedbackForOrderCommand()),
    USERS(new UsersCommand()),
    ADD_TO_USER_ACCOUNT(new AddToUserAccountCommand()),
    DELETE_USER(new DeleteUserCommand()),
    DELETE_ORDER(new DeleteOrderCommand()),
    CONFIRM_EMAIL(new ConfirmEmailCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    CLIENT_PAY_FOR_ORDER(new ClientPayForOrderCommand()),
    MANAGER_PAY_FOR_ORDER(new ManagerPayForOrderCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    SET_CRAFTSMAN_AND_PRICE(new SetCraftsmanAndPriceCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_PROFILE_SETTINGS(new ChangeProfileSettingsCommand());


    private final IActionCommand command;

    PostCommandEnum(IActionCommand command) {
        this.command = command;
    }

    public IActionCommand getCurrentCommand() {
        return command;
    }
}