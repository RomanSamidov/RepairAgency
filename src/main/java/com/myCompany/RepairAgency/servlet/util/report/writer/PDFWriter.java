package com.myCompany.RepairAgency.servlet.util.report.writer;

import com.myCompany.RepairAgency.model.entity.DTO.RepairOrderDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class PDFWriter{
    private int initX;
    private int initY;
    private final int cellHeight;
    private final int cellWidth;

    private PDFWriter(){
        initX = 30;
        cellHeight = 20;
        cellWidth = 20;
    }

    public static void createReport(ArrayList<RepairOrderDTO> repairOrders, Locale language, String filename)
            throws IOException {
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("LocalStrings", language);

        PDDocument document = new PDDocument();
        int i;
        int rowCount = repairOrders.size()-1;
        int rowCountOnPage= 0;

        while (rowCountOnPage != rowCount) {
            i = rowCountOnPage;
            if ((rowCount - rowCountOnPage) > 36) {
                rowCountOnPage += 36;
            } else {
                rowCountOnPage = rowCount;
            }

            PDPage page = new PDPage();
            page.setTrimBox(PDRectangle.A4);
            document.addPage(page);
            PDFWriter writer = new PDFWriter();
            int pageHeight = (int) page.getTrimBox().getHeight();
            writer.initY = pageHeight - 20;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setStrokingColor(Color.DARK_GRAY);
            contentStream.setLineWidth(1);
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12);

            createTableHeader(contentStream, writer, resourceBundle);

            for (; i <= rowCountOnPage; i++) {
                RepairOrderDTO order = repairOrders.get(i);
                createTableCell(contentStream, writer, 5, String.valueOf(order.getId()));
                createTableCell(contentStream, writer, 10, String.valueOf(order.getUser_id()));
                createTableCell(contentStream, writer, 10, order.getTextCraftsman_id());
                createTableCell(contentStream, writer, 100, order.getCreation_date());
                createTableCell(contentStream, writer, 20, String.valueOf(order.getPrice()));
                createTableCell(contentStream, writer, 100, order.getFinish_date());
                createTableCell(contentStream, writer, 30, resourceBundle.getString("text.order.status."
                        + order.getStatus() + ".short"));
                createTableCell(contentStream, writer, 100, order.getFeedback_date());

                writer.initX = 30;
                writer.initY -= writer.cellHeight;
            }
            contentStream.stroke();
            contentStream.close();
        }

        new File("reports").mkdirs();
        document.save("reports" + FileSystems.getDefault().getSeparator() + filename + ".pdf");
        document.close();
    }


    private static void createTableHeader( PDPageContentStream contentStream
            , PDFWriter writer
            , ResourceBundle resourceBundle) throws IOException
    {
        createTableCell(contentStream, writer,  5, resourceBundle.getString("text.order.id"));
        createTableCell(contentStream, writer,  10, resourceBundle.getString("text.order.user_id"));
        createTableCell(contentStream, writer,  10, resourceBundle.getString("text.order.craftsman_id"));
        createTableCell(contentStream, writer,  100, resourceBundle.getString("text.order.creation_date"));
        createTableCell(contentStream, writer,  20, resourceBundle.getString("text.order.price"));
        createTableCell(contentStream, writer,  100, resourceBundle.getString("text.order.finish_date"));
        createTableCell(contentStream, writer,  30, resourceBundle.getString("text.order.status"));
        createTableCell(contentStream, writer,  100, resourceBundle.getString("text.order.feedback_date"));
        writer.initX = 30;
        writer.initY -= writer.cellHeight;
    }

    private static void createTableCell( PDPageContentStream contentStream
            , PDFWriter writer
            , Integer cellWidth, String text) throws IOException {

        contentStream.addRect(writer.initX, writer.initY, writer.cellWidth + cellWidth, -writer.cellHeight);
        contentStream.beginText();
        contentStream.newLineAtOffset(writer.initX+5, writer.initY - writer.cellHeight + 5);
        contentStream.showText(text);
        contentStream.endText();
        writer.initX += writer.cellWidth + cellWidth;
    }
}
