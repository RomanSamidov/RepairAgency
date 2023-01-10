package com.myCompany.RepairAgency.servlet.util.report;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import com.myCompany.RepairAgency.servlet.util.report.writer.PDFWriter;
import com.myCompany.RepairAgency.servlet.util.report.writer.XLSWriter;
import com.myCompany.RepairAgency.servlet.util.report.writer.XLSXWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ReportManager {

    private ReportManager() {}

        public static String getReportWriter(ArrayList<RepairOrderDTO> repairOrders, Locale language, long userId,
                                             Constants.REPORT_FORMAT format) throws IOException {
            String filename;
        switch (format){
            case PDF:
                filename = "report[" + userId + "]";
                PDFWriter.createReport(repairOrders,language, filename);
                return filename + ".pdf";
            case XLS:
                filename = "report[" + userId + "]";
                XLSWriter.createReport(repairOrders,language, filename);
                return filename + ".xls";
            case XLSX:
                filename = "report[" + userId + "]";
                XLSXWriter.createReport(repairOrders,language, filename);
                return filename + ".xlsx";
        }
        throw new RuntimeException("Wrong REPORT_FORMAT");
    }
}
