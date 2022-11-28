package com.myCompany.RepairAgency;


import com.myCompany.RepairAgency.db.DBManager;
import com.myCompany.RepairAgency.db.entity.OrderStatus;
import com.myCompany.RepairAgency.db.entity.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DBManager dbManager=new DBManager();

        User userPetrov=dbManager.getUser(1);
        System.out.println(userPetrov.getLogin());

        ArrayList<OrderStatus> statuses=dbManager.getAllOrderStatuses();
        statuses.forEach(a -> System.out.println(a.getId() + " " + a.getName()));
    }

}