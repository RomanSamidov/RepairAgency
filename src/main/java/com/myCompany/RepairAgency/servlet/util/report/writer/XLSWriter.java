package com.myCompany.RepairAgency.servlet.util.report.writer;

import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class XLSWriter {
    public static void createReport(ArrayList<RepairOrderDTO> repairOrders, Locale language, String filename)
            throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("LocalStrings", language);

        Sheet sheet = workbook.createSheet("Orders");
        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 2000);
        sheet.setColumnWidth(3, 7000);
        sheet.setColumnWidth(4, 2000);
        sheet.setColumnWidth(5, 7000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 7000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        createTableHeader(header, headerStyle, resourceBundle);

        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setWrapText(true);

        final int[] i = {1};
        repairOrders.forEach(o ->{
            Row row = sheet.createRow(i[0]++);
            createTableRow(row, style, resourceBundle, o);
        });


        new File("reports").mkdirs();
        String fileLocation = "reports" + FileSystems.getDefault().getSeparator() + filename + ".xls";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

    private static void createTableHeader( Row header, CellStyle headerStyle, ResourceBundle resourceBundle){
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue(resourceBundle.getString("text.order.id"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue(resourceBundle.getString("text.order.user_id"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue( resourceBundle.getString("text.order.craftsman_id"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue( resourceBundle.getString("text.order.creation_date"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue( resourceBundle.getString("text.order.price"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue( resourceBundle.getString("text.order.finish_date"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(6);
        headerCell.setCellValue( resourceBundle.getString("text.order.status"));
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(7);
        headerCell.setCellValue( resourceBundle.getString("text.order.feedback_date"));
        headerCell.setCellStyle(headerStyle);
    }

    private static void createTableRow( Row row, CellStyle headerStyle, ResourceBundle resourceBundle,
                                        RepairOrderDTO order){
        Cell headerCell = row.createCell(0);
        headerCell.setCellValue(order.getId());
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(1);
        headerCell.setCellValue(order.getUser_id());
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(2);
        headerCell.setCellValue(order.getTextCraftsman_id());
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(3);
        headerCell.setCellValue(order.getCreation_date());
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(4);
        headerCell.setCellValue(order.getPrice());
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(5);
        headerCell.setCellValue(order.getFinish_date());
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(6);
        headerCell.setCellValue( resourceBundle.getString("text.order.status." + order.getStatus()));
        headerCell.setCellStyle(headerStyle);

        headerCell = row.createCell(7);
        headerCell.setCellValue( order.getFeedback_date());
        headerCell.setCellStyle(headerStyle);
    }
}
