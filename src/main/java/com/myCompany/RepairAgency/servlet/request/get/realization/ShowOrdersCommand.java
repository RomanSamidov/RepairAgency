package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.abstractRepository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import com.myCompany.RepairAgency.servlet.util.report.ReportManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowOrdersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = PathFactory.getPath("path.page.forward.orders");


        request.setAttribute("title", "title.my_orders");
        long[] craftIds = new long[]{}, statusIds;

        if (request.getSession().getAttribute("craftsmanIdOrders") != null) {
            long[] tempCraftIds = ((long[]) request.getSession().getAttribute("craftsmanIdOrders"));
            if (tempCraftIds[0] != 0) craftIds = tempCraftIds;
        }

        long userId = 0;

        if(request.getSession().getAttribute("userRole").equals(Constants.ROLE.Client)) userId = (long) request.getSession().getAttribute("userId");

        if (request.getSession().getAttribute("statusOrders") != null) {
            long[] tempStatusIds = ((long[]) request.getSession().getAttribute("statusOrders"));
            if (tempStatusIds[0] != 0) {
                statusIds = tempStatusIds;
            } else statusIds = Arrays.stream(Constants.ORDER_STATUS.values()).mapToLong(Constants.ORDER_STATUS::getOrdinal).toArray();
        } else {
            statusIds = Arrays.stream(Constants.ORDER_STATUS.values()).mapToLong(Constants.ORDER_STATUS::getOrdinal).toArray();
        }

        iRepairOrderRepository.SORT_TYPE sortType;
        if (request.getSession().getAttribute("sortTypeOrders") != null) {
            sortType = ((iRepairOrderRepository.SORT_TYPE) request.getSession().getAttribute("sortTypeOrders"));
        }else {
            sortType = iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC;
        }

         if (request.getSession().getAttribute("userRole") == Constants.ROLE.Craftsman) {
            craftIds = new long[]{(long) request.getSession().getAttribute("userId")};
        }
         request.setAttribute("title", "title.orders");




        iRepairOrderRepository orderRepository = ModelManager.ins.getRepairOrderRepository();
        long numberOfOrders;
        numberOfOrders = orderRepository.countByCraftUserStatus(craftIds, userId, statusIds);

        int[] a = ForTables.initSkipQuantity("Orders",numberOfOrders, request);
        int skip = a[0];
        int quantity = a[1];

        if (request.getSession().getAttribute("createReport") != null && (boolean) request.getSession().getAttribute("createReport")) {
            try {
                page = createReport(request, numberOfOrders, craftIds, userId, statusIds, sortType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(orderRepository.getByCraftUserStatus(craftIds, userId, statusIds, sortType, skip, quantity)));
        ForTables.updatePagesForJSP(quantity, skip, numberOfOrders, "Orders", request);
        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);

        request.setAttribute("orderStatuses", orderStatuses);
        request.setAttribute("reportFormats", Constants.REPORT_FORMAT.values());
        request.setAttribute("sortTypeOrders", sortType);
        request.setAttribute("sortTypesOrders", iRepairOrderRepository.SORT_TYPE.values());
        request.setAttribute("craftsmen", UserDTOFactory.getUsers(ModelManager.ins.getUserRepository().getByRole(Constants.ROLE.Craftsman.ordinal(), 0, 50)));
        return page;
    }


    private Path createReport(HttpServletRequest request, long numberOfOrders, long[] craftIds, long userId, long[] statusIds
            , iRepairOrderRepository.SORT_TYPE  sortType) throws IOException {
        Locale language = new Locale("en_US");
        if(request.getSession().getAttribute("locale") instanceof  String) Locale.forLanguageTag((String) request.getSession().getAttribute("locale"));
        if(request.getSession().getAttribute("locale") instanceof  Locale) language = (Locale) request.getSession().getAttribute("locale");
        String filename;
        Constants.REPORT_FORMAT format = (Constants.REPORT_FORMAT) request.getSession().getAttribute("reportFormat");
        iRepairOrderRepository orderRepository = ModelManager.ins.getRepairOrderRepository();

        filename = ReportManager.getReportWriter(RepairOrderDTOFactory.getRepairOrders(orderRepository.getByCraftUserStatus(craftIds, userId, statusIds, sortType, 0, numberOfOrders))
        , language, userId, format);

        request.getSession().setAttribute("orderName", filename);
        request.getSession().setAttribute("createReport", false);
        return PathFactory.getPath("path.page.redirect.download");
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client, Constants.ROLE.Admin, Constants.ROLE.Craftsman, Constants.ROLE.Manager).collect(Collectors.toList());
    }
}