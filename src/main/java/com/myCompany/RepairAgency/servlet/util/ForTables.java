package com.myCompany.RepairAgency.servlet.util;

import jakarta.servlet.http.HttpServletRequest;


public class ForTables {
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
            if ((long) request.getSession().getAttribute("numberOf" + tableName) != numberOf) {
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