package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.model.entity.DTO.UserDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.ViewValidationService;
import com.myCompany.RepairAgency.servlet.util.ForTables;
import com.myCompany.RepairAgency.servlet.util.report.ReportManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowOrdersCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        Path page = ViewValidationService.validateForOrdersPage(request);
        Constants.ROLE userRole = (Constants.ROLE) request.getSession().getAttribute("userRole");
        setPageTittle(request, userRole);

        request.setAttribute("errorOrderTextMessage",
                request.getSession().getAttribute("errorOrderTextMessage"));
        request.setAttribute("errorOrderClientIdMessage",
                request.getSession().getAttribute("errorOrderClientIdMessage"));
        request.getSession().removeAttribute("errorOrderTextMessage");

        long[] craftIds = initCraftsmenIds(request, userRole);
        long[] statusIds = initStatusIds(request);
        long userId = initUserId(request, userRole);

        iRepairOrderRepository.SORT_TYPE sortType = initSortType(request);

        iRepairOrderRepository orderRepository = ModelManager.getInstance().getRepairOrderRepository();
        long numberOfOrders = orderRepository.countByCraftUserStatus(craftIds, userId, statusIds);

        int[] a = ForTables.initSkipQuantity("Orders", numberOfOrders, request);
        int skip = a[0];
        int quantity = a[1];

        if (request.getSession().getAttribute("createReport") != null &&
                (boolean) request.getSession().getAttribute("createReport")) {
                page = createReport(request, numberOfOrders, craftIds, userId, statusIds, sortType);
        }

        if (numberOfOrders == 0){
            request.setAttribute("error", "text.there_are_no_orders");
        }
        request.setAttribute("orders", RepairOrderDTOFactory.getRepairOrders(
                orderRepository.getByCraftUserStatus(craftIds, userId, statusIds, sortType, skip, quantity)));

        ArrayList<Constants.ORDER_STATUS> orderStatuses = new ArrayList<>(List.of(Constants.ORDER_STATUS.values()));
        orderStatuses.remove(0);

        request.setAttribute("orderStatuses", orderStatuses);
        request.setAttribute("reportFormats", Constants.REPORT_FORMAT.values());
        request.setAttribute("sortTypeOrders", sortType);
        request.setAttribute("sortTypesOrders", iRepairOrderRepository.SORT_TYPE.values());
        request.setAttribute("craftsmen", UserDTOFactory.getUsers(
                ModelManager.getInstance().getUserRepository()
                        .getByRole(Constants.ROLE.Craftsman.ordinal(), 0, 50)));
        return page;
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Craftsman,
                Constants.ROLE.Manager).collect(Collectors.toList());
    }

    private void setPageTittle(HttpServletRequest request, Constants.ROLE userRole) {
        if(userRole.equals(Constants.ROLE.Admin) || userRole.equals(Constants.ROLE.Manager)){
            request.setAttribute("title", "title.orders");
        } else request.setAttribute("title", "title.my_orders");
    }

    private long[] initCraftsmenIds(HttpServletRequest request, Constants.ROLE userRole){
        if (userRole.equals(Constants.ROLE.Craftsman)) {
            return new long[]{(long) request.getSession().getAttribute("userId")};
        } else
        if (request.getSession().getAttribute("craftsmanIdOrders") != null) {
            long[] tempCraftIds = ((long[]) request.getSession().getAttribute("craftsmanIdOrders"));
            if (tempCraftIds[0] != 0) return tempCraftIds;
        }
        return new long[]{};
    }

    private long[] initStatusIds(HttpServletRequest request){
        if (request.getSession().getAttribute("statusOrders") != null) {
            long[] tempStatusIds = ((long[]) request.getSession().getAttribute("statusOrders"));
            if (tempStatusIds[0] != 0) {
                return tempStatusIds;
            }
        }
        return new long[]{};
//        return Arrays.stream(Constants.ORDER_STATUS.values()).mapToLong(Constants.ORDER_STATUS::getOrdinal).toArray();
    }

    private long initUserId(HttpServletRequest request, Constants.ROLE userRole) {
        if(userRole.equals(Constants.ROLE.Client)) return  (long) request.getSession().getAttribute("userId");
        return 0;
    }

    private iRepairOrderRepository.SORT_TYPE initSortType (HttpServletRequest request) {
        if (request.getSession().getAttribute("sortTypeOrders") != null) {
            return  ((iRepairOrderRepository.SORT_TYPE) request.getSession().getAttribute("sortTypeOrders"));
        }else {
            return iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC;
        }
    }

    private Path createReport(HttpServletRequest request, long numberOfOrders,
                              long[] craftIds, long userId, long[] statusIds
            , iRepairOrderRepository.SORT_TYPE  sortType) {
        try {
            Locale language =new Locale((String) request.getSession().getAttribute("language"));
            String filename;
            Constants.REPORT_FORMAT format =
                    (Constants.REPORT_FORMAT) request.getSession().getAttribute("reportFormat");
            iRepairOrderRepository orderRepository = ModelManager.getInstance().getRepairOrderRepository();

            filename = ReportManager.getReportWriter(
                    RepairOrderDTOFactory.getRepairOrders(
                            orderRepository.getByCraftUserStatus(
                                    craftIds,
                                    userId,
                                    statusIds,
                                    sortType, 0, numberOfOrders))
                    , language, userId, format);

            request.getSession().setAttribute("reportName", filename);
            request.getSession().setAttribute("createReport", false);
            return PathFactory.getPath("path.page.redirect.download");
        } catch (IOException e) {
            return PathFactory.getPath("path.page.forward.error");
        }
    }


}