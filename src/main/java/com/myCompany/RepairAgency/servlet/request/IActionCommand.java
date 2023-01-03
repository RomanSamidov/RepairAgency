package com.myCompany.RepairAgency.servlet.request;

import com.myCompany.RepairAgency.servlet.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface IActionCommand {
    Path execute(HttpServletRequest request, HttpServletResponse response);
}