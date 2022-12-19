package com.myCompany.RepairAgency.servlet.request;

import jakarta.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request);
}