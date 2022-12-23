package com.myCompany.RepairAgency.servlet.request;

import com.myCompany.RepairAgency.Constants;

import java.util.List;

public interface IHasRoleRequirement {
    List<Constants.ROLE> allowedUserRoles();
}
