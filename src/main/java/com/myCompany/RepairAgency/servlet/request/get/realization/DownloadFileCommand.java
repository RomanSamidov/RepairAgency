package com.myCompany.RepairAgency.servlet.request.get.realization;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.servlet.Path;
import com.myCompany.RepairAgency.servlet.PathFactory;
import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.IHasRoleRequirement;
import com.myCompany.RepairAgency.servlet.util.SendEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DownloadFileCommand implements IActionCommand, IHasRoleRequirement {
    private static final Logger logger = LogManager.getLogger(DownloadFileCommand.class);

    @Override
    public Path execute(HttpServletRequest request, HttpServletResponse response) {
        String filename = "reports/" + request.getSession().getAttribute("reportName");
        logger.debug("Preparation of downloading file " + filename);
        SendEmail.forDownload(request, filename);
        setResponseHead(response, filename);
        writeFileToResponse(response, filename);
        if (new File(filename).delete()) logger.debug("File " + filename + " deleted.");
        logger.debug("Downloading have to start.");
        request.getSession().removeAttribute("reportName");
        return PathFactory.getPath("path.page.redirect.home", true);
    }


    private void setResponseHead(HttpServletResponse response, String filename) {
        response.setContentType("text/html");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
//        response.setContentType("application/pdf");
    }

    private void writeFileToResponse(HttpServletResponse response, String filename) {
        try {
            OutputStream out;
            out = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(filename);
            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.write(233);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Constants.ROLE> allowedUserRoles() {
        return Stream.of(Constants.ROLE.Client,
                Constants.ROLE.Craftsman,
                Constants.ROLE.Manager,
                Constants.ROLE.Admin).collect(Collectors.toList());
    }
}