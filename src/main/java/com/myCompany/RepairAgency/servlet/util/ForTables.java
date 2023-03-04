package com.myCompany.RepairAgency.servlet.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Class for pagination on showed on pages tables.
 */
public class ForTables {
    /**
     * Method for initialization from session Skip and Quantity for query from DB.
     * @param tableName Name of table for which is return values are.
     * @param numberOf How many elements in DB. From which we are raking page.
     * @return Array of 2 elements, in which 0 is Skip, and 1 is quantity values.
     */
    public static int[] initSkipQuantity(String tableName, long numberOf, HttpServletRequest request) {
        Integer page = (Integer) request.getSession().getAttribute("nowPageFor" + tableName);
        Integer quantity = (Integer) request.getSession().getAttribute("nowQuantityFor" + tableName);
        if (quantity == null || quantity == 0) {
            quantity = 5;
            request.getSession().setAttribute("nowQuantityFor" + tableName, quantity);
        }
        if (page == null || page == 0) {
            page = 1;
            request.getSession().setAttribute("nowPageFor" + tableName, page);
        }
        if (request.getSession().getAttribute("numberOf" + tableName) != null) {
            if ((long) request.getSession().getAttribute("numberOf" + tableName) > numberOf) {
                page = 1;
                request.getSession().setAttribute("nowPageFor" + tableName, page);
            }
        }
        request.getSession().setAttribute("numberOf" + tableName, numberOf);

        int pages = (int) Math.ceil(((double) numberOf) / ((double) quantity));
        if (pages == 0) pages = 1;
        request.setAttribute("maxPageFor" + tableName, pages);
        if (page > pages) {
            page = pages;
            request.getSession().setAttribute("nowPageFor" + tableName, page);
        }

        return new int[]{(page - 1) * quantity, quantity};
    }

    /**
     * Method that move new values for page for pagination and quantity, from request to session.
     * @param tableName Name of table for which is method calling.
     */
    public static void updateSkipQuantity(String tableName, HttpServletRequest request) {
        if (request.getParameter("newQuantityFor" + tableName) != null) {
            int quantity = Integer.parseInt(request.getParameter("newQuantityFor" + tableName));
            if (quantity <= 0) quantity = 5;
            request.getSession().setAttribute("nowQuantityFor" + tableName, quantity);
            request.getSession().setAttribute("nowPageFor" + tableName, 1);
        } else if (request.getParameter("goToPageFor" + tableName) != null) {
            int page = Integer.parseInt(request.getParameter("goToPageFor" + tableName));
            if (page <= 0) page = 1;
            request.getSession().setAttribute("nowPageFor" + tableName, page);
        }
    }
}