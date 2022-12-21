package com.myCompany.RepairAgency.servlet.request;

import com.myCompany.RepairAgency.servlet.Path;
import jakarta.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Path execute(HttpServletRequest request);
}