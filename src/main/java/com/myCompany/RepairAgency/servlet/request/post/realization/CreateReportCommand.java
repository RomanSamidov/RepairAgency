package com.myCompany.RepairAgency.servlet.request.post.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTOFactory;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.service.RepairOrderService;
import com.myCompany.RepairAgency.servlet.util.InitValuesFromRequest;
import com.myCompany.RepairAgency.servlet.util.report.ReportManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateReportCommand implements IActionCommand, IHasRoleRequirement {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        long[] craftIds = InitValuesFromRequest.initCraftsmenIds(request);
        long[] statusIds = InitValuesFromRequest.initStatusIds(request);
        long userId = InitValuesFromRequest.initUserId(request);
        iRepairOrderRepository.SORT_TYPE sortType = InitValuesFromRequest.initSortType(request);

        long numberOfOrders = RepairOrderService.countByCraftUserStatus(craftIds, userId, statusIds);

        Constants.LOCALE language = Constants.LOCALE.valueOf(
                (String) request.getSession().getAttribute("language"));

        String filename;
        Constants.REPORT_FORMAT format;

        try {
            format = Constants.REPORT_FORMAT.valueOf(request.getParameter("reportFormat"));
        } catch (IllegalArgumentException e) {
            format = Constants.REPORT_FORMAT.XLS;
        }


        try {
            filename = ReportManager.getReportWriter(
                    RepairOrderDTOFactory.getRepairOrders(
                            RepairOrderService.getByCraftUserStatus(
                                    craftIds,
                                    userId,
                                    statusIds,
                                    sortType, 0L, numberOfOrders))
                    , language, userId, format);
        } catch (IOException e) {
            return PathFactory.getPath("path.page.forward.error");
        }
        request.getSession().setAttribute("reportName", filename);
        return PathFactory.getPath("path.page.redirect.download");
    }


    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Admin,
                Constants.ROLE.Craftsman,
                Constants.ROLE.Manager).collect(Collectors.toList());
    }

}