package com.myCompany.RepairAgency.servlet.util.report;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ReportManager {

    private ReportManager() {}

        public static String getReportWriter(ArrayList<RepairOrderDTO> repairOrders, Locale language, long userId, Constants.REPORT_FORMAT format) throws IOException {
        if (format == Constants.REPORT_FORMAT.PDF) {
            String filename = "report[" + userId + "]";
             PDFWriter.createReport(repairOrders,language, filename);
             return filename + ".pdf";
        }
        throw new RuntimeException("Wrong REPORT_FORMAT");
    }
}
