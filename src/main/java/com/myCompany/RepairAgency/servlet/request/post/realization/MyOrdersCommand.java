package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MyOrdersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request) {
        Integer skip;
        if(request.getParameter("skipOrders") != null) {
            skip = Integer.valueOf(request.getParameter("skipOrders"));
            if (skip <= 0) skip = 0;
            request.getSession().setAttribute("skipOrders", skip);
        }
        skip = (Integer) request.getSession().getAttribute("skipOrders");
        if (skip == null || skip <= 0) skip = 0;

        Integer quantity;
        if(request.getParameter("quantityOrders") != null) {
            quantity = Integer.valueOf(request.getParameter("quantityOrders"));
            if (quantity <= 0) quantity = 5;
            request.getSession().setAttribute("quantityOrders", quantity);
        }
        quantity = (Integer) request.getSession().getAttribute("quantityOrders");
        if (quantity == null || quantity <= 0) quantity = 5;

        Long userId = (Long) request.getSession().getAttribute("userId");

        long numberOfOrders = ModelManager.ins.getCountRepairOrdersWhereUserIdIs(userId);


        int pages = (int) Math.ceil(((double)numberOfOrders)/((double)quantity));
        int nowPage = 0;
        if( pages > 0) {
             nowPage = skip / quantity;
        }

        request.getSession().setAttribute("nowPageOrders", nowPage);

        Integer finalQuantity = quantity;
        List<Integer> listPages = IntStream.iterate(0, x -> x + finalQuantity).limit(pages).boxed().collect(Collectors.toList());

        request.getSession().setAttribute("listPagesOrders", listPages);

        return PathFactory.getPath("path.page.redirect.my_orders");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client).collect(Collectors.toList());
    }
}