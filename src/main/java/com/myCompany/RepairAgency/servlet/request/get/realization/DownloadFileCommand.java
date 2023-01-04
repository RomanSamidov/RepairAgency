package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadFileCommand implements IActionCommand {
    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String filename = "table.pdf";
        String filename = (String) request.getSession().getAttribute("orderName");
        String filepath = "reports/";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
////
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
//        response.setContentType("application/pdf");

////
        try {
            OutputStream out;
            out = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(filepath + filename);

            int i;
            while ((i=fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.write(233);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new File(filepath + filename).delete();
        return PathFactory.getPath("path.page.forward.index");
    }

}