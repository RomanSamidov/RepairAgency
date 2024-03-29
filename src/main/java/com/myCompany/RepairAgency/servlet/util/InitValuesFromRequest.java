package com.myCompany.RepairAgency.servlet.util;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Class contains code for getting values from {@link jakarta.servlet.http.HttpServletRequest}
 */
public class InitValuesFromRequest {

    /**
     * @return Array of ids selected craftsmen for order selection, empty if no one selected
     */
    public static long[] initCraftsmenIds(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");

        if (userRole.equals(Constants.ROLE.Craftsman)) {
            return new long[]{(long) session.getAttribute("userId")};
        } else if (session.getAttribute("craftsmanIdOrders") != null) {
            long[] tempCraftIds = ((long[]) session.getAttribute("craftsmanIdOrders"));
            if (tempCraftIds[0] != 0) return tempCraftIds;
        }
        return new long[]{};
    }

    /**
     * @return Array of ids selected statuses for order selection, empty if no one selected
     */
    public static long[] initStatusIds(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("statusOrders") != null) {
            long[] tempStatusIds = ((long[]) session.getAttribute("statusOrders"));
            if (tempStatusIds[0] != 0) {
                return tempStatusIds;
            }
        }
        return new long[]{};
    }

    /**
     * @return Client id for which show orders list, 0 if user not client and can see anything.
     */
    public static long initUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Constants.ROLE userRole = (Constants.ROLE) session.getAttribute("userRole");

        if (userRole.equals(Constants.ROLE.Client)) return (long) session.getAttribute("userId");
        return 0L;
    }

    /**
     * @return Get selected sort type for orders list.
     */
    public static iRepairOrderRepository.SORT_TYPE initSortType(HttpServletRequest request) {
        if (request.getSession().getAttribute("sortTypeOrders") != null) {
            return ((iRepairOrderRepository.SORT_TYPE) request.getSession().getAttribute("sortTypeOrders"));
        } else {
            return iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC;
        }
    }

    /**
     * @return Get ordinal of selected user role for users list.
     */
    public static long initRoleId(HttpServletRequest request) {
        try {
            if (request.getSession().getAttribute("roleUsers") != null) {
                Constants.ROLE role = (Constants.ROLE) request.getSession().getAttribute("roleUsers");
                return role.ordinal();
            } else {
                if (request.getSession().getAttribute("userRole").equals(Constants.ROLE.Admin)) {
                    return 0;
                } else {
                    request.getSession().setAttribute("roleUsers", Constants.ROLE.Client);
                    return Constants.ROLE.Client.ordinal();
                }
            }
        } catch (Exception e) {
            request.getSession().setAttribute("roleUsers", Constants.ROLE.Client);
            return Constants.ROLE.Client.ordinal();
        }
    }

    public static long initGoalId(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            try {
                long id = Long.parseLong(request.getParameter("id"));
                if (id < 0) id = 0;
                return id;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

}
