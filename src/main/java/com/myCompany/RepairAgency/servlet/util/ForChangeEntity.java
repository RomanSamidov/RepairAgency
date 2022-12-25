package com.myCompany.RepairAgency.servlet.util;

import jakarta.servlet.http.HttpServletRequest;

public class ForChangeEntity {

    public static long initGoalId(String goalName, HttpServletRequest request){
        Long goalId = (Long) request.getSession().getAttribute("goalId" + goalName);
        if (goalId == null) goalId = 0L;
        return goalId;
    }

    public static void updateGoalId(String goalName, HttpServletRequest request){
        if(request.getParameter("goalId" + goalName) != null) {
            long goalId = Long.parseLong(request.getParameter("goalId" + goalName));
            if (goalId <= 0) goalId = 0;
            request.getSession().setAttribute("goalId" + goalName, goalId);
        }

    }
}
