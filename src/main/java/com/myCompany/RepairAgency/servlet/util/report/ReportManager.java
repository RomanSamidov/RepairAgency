package com.myCompany.RepairAgency.servlet.util.report;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import com.myCompany.RepairAgency.servlet.util.report.writer.PDFWriter;
import com.myCompany.RepairAgency.servlet.util.report.writer.XLSWriter;
import com.myCompany.RepairAgency.servlet.util.report.writer.XLSXWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for creating file reports of different file types.
 */
public class ReportManager {

    private ReportManager() {
    }

    /**
     * Method for creating file with report, in different file types.
     * @param repairOrders list of orders with witch report will be created
     * @param language language with witch report will be created
     * @param userId used only for file name, in order to prevent errors with file rewriting
     * @param format format in which make report
     * @return Name of result file report
     */
    public static String getReportWriter(ArrayList<RepairOrderDTO> repairOrders, Constants.LOCALE language, long userId,
                                         Constants.REPORT_FORMAT format) throws IOException
    {
        String filename = "report[" + userId + "]";

        switch (format) {
            case PDF -> {
                PDFWriter.createReport(repairOrders, language, filename);
                return filename + ".pdf";
            }
            case XLS -> {
                XLSWriter.createReport(repairOrders, language, filename);
                return filename + ".xls";
            }
            case XLSX -> {
                XLSXWriter.createReport(repairOrders, language, filename);
                return filename + ".xlsx";
            }
        }
        throw new RuntimeException("Wrong REPORT_FORMAT");
    }
}
