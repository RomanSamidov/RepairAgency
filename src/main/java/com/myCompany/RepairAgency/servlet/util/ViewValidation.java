package com.myCompany.RepairAgency.servlet.util;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.model.entity.RepairOrder;
import com.myCompany.RepairAgency.model.entity.User;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * This class responsible for selecting parts of pages that user can see on page which he looks.
 */
public class ViewValidation {

    public static void setMenu(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");
        switch (userRole) {
            case Client ->
                    session.setAttribute("_menu_url", PathFactory.getPath("path.template.menu.client").toString());
            case Guest -> session.setAttribute("_menu_url", PathFactory.getPath("path.template.menu.guest").toString());
            case Craftsman ->
                    session.setAttribute("_menu_url", PathFactory.getPath("path.template.menu.craftsman").toString());
            case Manager ->
                    session.setAttribute("_menu_url", PathFactory.getPath("path.template.menu.manager").toString());
            case Admin -> session.setAttribute("_menu_url", PathFactory.getPath("path.template.menu.admin").toString());
        }
    }


    public static void validateForChangePassword(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("waitedCodePassword") != null) {
            session.setAttribute("_part_of_change_password_url",
                    PathFactory.getPath("path.page.forward.common.parts.part_of_change_password").toString());
        } else {
            session.setAttribute("_part_of_change_password_url",
                    PathFactory.getPath("path.page.forward.common.empty").toString());
        }
    }

    private static boolean checkAccessibility(HttpServletRequest request, RepairOrder order) {
        if ((request.getSession().getAttribute("userRole")).equals(Constants.ROLE.Client)) {
            return (long) request.getSession().getAttribute("userId") == order.getUser_id();
        } else if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Craftsman)) {
            return (long) request.getSession().getAttribute("userId") == order.getCraftsman_id();
        }
        return true;
    }

    public static Path validateForOrderPage(HttpServletRequest request, RepairOrder order) {
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        request.setAttribute("_show_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        request.setAttribute("_client_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        request.setAttribute("_cancel_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        request.setAttribute("_delete_order_url", PathFactory.getPath("path.page.forward.common.empty").toString());

        Path path = switch (userRole) {
            case Guest -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.order");
            case Manager -> PathFactory.getPath("path.page.forward.manager.order");
            case Craftsman -> PathFactory.getPath("path.page.forward.craftsman.order");
            case Client -> PathFactory.getPath("path.page.forward.client.order");
        };

        if (!checkAccessibility(request, order)) {
            request.setAttribute("error", "message.not_allowed");
            return path;
        } else {
            request.setAttribute("goalOrder", RepairOrderDTOFactory.getRepairOrder(order));
            request.setAttribute("_delete_order_url", PathFactory.getPath("path.page.order.part.client.delete_order").toString());
            request.setAttribute("_show_order_url", PathFactory.getPath("path.page.order.part.show_order").toString());
        }


        if ((userRole == Constants.ROLE.Craftsman || userRole == Constants.ROLE.Admin)) {
            if (order.getStatus_id() == Constants.ORDER_STATUS.PAID.ordinal()) {
                request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.order.part.craftsman.take_order").toString());
            } else if (order.getStatus_id() == Constants.ORDER_STATUS.IN_PROGRESS.ordinal()) {
                request.setAttribute("_craft_order_url", PathFactory.getPath("path.page.order.part.craftsman.complete_order").toString());
            }
        }

        if ((userRole == Constants.ROLE.Client || userRole == Constants.ROLE.Admin)) {
            if (order.getStatus_id() == Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal() && userRole != Constants.ROLE.Admin) {
                request.setAttribute("_client_order_url", PathFactory.getPath("path.page.order.part.client.pay_order").toString());
            } else if (order.getStatus_id() == Constants.ORDER_STATUS.COMPLETED.ordinal()) {
                request.setAttribute("_client_order_url", PathFactory.getPath("path.page.order.part.client.set_feedback").toString());
            }
        }

        if ((userRole == Constants.ROLE.Manager || userRole == Constants.ROLE.Admin)) {
            if (order.getStatus_id() == Constants.ORDER_STATUS.PENDING_PAYMENT.ordinal()) {
                request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.order.part.manager.pay_order").toString());
            } else if (order.getStatus_id() == Constants.ORDER_STATUS.CREATED.ordinal()) {
                request.setAttribute("_manager_order_url", PathFactory.getPath("path.page.order.part.manager.set_craftsman_and_price").toString());
            }
        }

        if (order.getStatus_id() != Constants.ORDER_STATUS.COMPLETED.ordinal() &&
                order.getStatus_id() != Constants.ORDER_STATUS.CANCELED.ordinal() &&
                checkAccessibility(request, order)) {
            request.setAttribute("_cancel_order_url", PathFactory.getPath("path.page.order.part.cancel_order").toString());
        }

        return path;
    }


    public static Path validateForOrdersPage(HttpServletRequest request) {
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        return switch (userRole) {
            case Guest -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.orders");
            case Manager -> PathFactory.getPath("path.page.forward.manager.orders");
            case Craftsman -> PathFactory.getPath("path.page.forward.craftsman.orders");
            case Client -> PathFactory.getPath("path.page.forward.client.orders");
        };
    }


    public static Path validateForProfilePage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if ((boolean) session.getAttribute("isUserConfirmed")) {
            request.setAttribute("_email_confirmed_url", PathFactory.getPath("path.page.cabinet.part.email_confirmed").toString());
        } else if (session.getAttribute("waitedCode") != null) {
            request.setAttribute("_email_confirmed_url", PathFactory.getPath("path.page.cabinet.part.email_not_confirmed_with_code").toString());
        } else {
            request.setAttribute("_email_confirmed_url", PathFactory.getPath("path.page.cabinet.part.email_not_confirmed_without_code").toString());
        }

        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");

        return switch (userRole) {
            case Guest -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.cabinet");
            case Manager -> PathFactory.getPath("path.page.forward.manager.cabinet");
            case Craftsman -> PathFactory.getPath("path.page.forward.craftsman.cabinet");
            case Client -> PathFactory.getPath("path.page.forward.client.cabinet");
        };
    }


    public static Path validateForUserPage(HttpServletRequest request) {

        long userId = InitValuesFromRequest.initGoalId(request);
        if (userId == 0) {
            userId = (long) request.getSession().getAttribute("userId");
        }

        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        User user = UserService.get(userId);
        if (user == null) {
            request.setAttribute("error", "text.there_are_no_users");
            request.setAttribute("_show_user_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            request.setAttribute("_add_to_account_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            request.setAttribute("_delete_user_url", PathFactory.getPath("path.page.forward.common.empty").toString());
        } else {
            if (userRole.equals(Constants.ROLE.Admin) && userId != (long) request.getSession().getAttribute("userId")) {
                request.setAttribute("_delete_user_url", PathFactory.getPath("path.page.user.part.admin.delete_user").toString());
            } else {
                request.setAttribute("_delete_user_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            }
            if (user.getRole_id() == Constants.ROLE.Client.ordinal()) {
                request.setAttribute("_add_to_account_url", PathFactory.getPath("path.page.user.part.manager.add_to_account").toString());
            } else {
                request.setAttribute("_add_to_account_url", PathFactory.getPath("path.page.forward.common.empty").toString());
            }


            request.setAttribute("_show_user_url", PathFactory.getPath("path.page.user.part.show_user").toString());
            request.setAttribute("goalUser", UserDTOFactory.getUser(user));
        }
        return switch (userRole) {
            case Guest, Craftsman, Client -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.user");
            case Manager -> PathFactory.getPath("path.page.forward.manager.user");
        };
    }


    public static Path validateForUsersPage(HttpServletRequest request) {
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        return switch (userRole) {
            case Guest, Craftsman, Client -> null;
            case Admin -> PathFactory.getPath("path.page.forward.admin.users");
            case Manager -> PathFactory.getPath("path.page.forward.manager.users");
        };
    }

}
