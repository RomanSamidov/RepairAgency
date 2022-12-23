package com.myCompany.RepairAgency.servlet.request;

import jakarta.servlet.http.HttpServletRequest;

public abstract class abstractCommandFactory {
    public abstract IActionCommand defineCommand(HttpServletRequest request);
}
