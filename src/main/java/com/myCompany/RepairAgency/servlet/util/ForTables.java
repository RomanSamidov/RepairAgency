package com.myCompany.RepairAgency.servlet.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ForTables {
    public static int[] initSkipQuantity(String tableName, HttpServletRequest request){
        Integer skip = (Integer) request.getSession().getAttribute("skip" + tableName);
        Integer quantity = (Integer) request.getSession().getAttribute("quantity" + tableName);
        if (quantity == null || quantity == 0) quantity = 5;
        if (skip == null) skip = 0;
        return  new int[]{skip, quantity};
    }

    public static void updateSkipQuantity(String tableName, HttpServletRequest request){
        if(request.getParameter("quantity" + tableName) != null) {
            int quantity = Integer.parseInt(request.getParameter("quantity" + tableName));
            if (quantity <= 0) quantity = 5;
            request.getSession().setAttribute("quantity" + tableName, quantity);
            request.getSession().setAttribute("skip" + tableName, 0);
        } else
            if(request.getParameter("skip" + tableName) != null) {
                int skip = Integer.parseInt(request.getParameter("skip" + tableName));
                if (skip <= 0) skip = 0;
                request.getSession().setAttribute("skip" + tableName, skip);
        }
    }


    public static void updatePagesForJSP(Integer quantity, int skip, long numberOfGoal, String tableName, HttpServletRequest request){
        int pages = (int) Math.ceil(((double)numberOfGoal)/((double)quantity));
        int nowPage = 0;
        if( pages > 0) {
            nowPage = skip / quantity;
        }
        request.getSession().setAttribute("nowPage" + tableName, nowPage);
        List<Integer> listPages = IntStream.iterate(0, x -> x + quantity).limit(pages).boxed().collect(Collectors.toList());
        request.getSession().setAttribute("listPages" + tableName, listPages);
    }
}
