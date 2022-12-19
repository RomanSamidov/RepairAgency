package com.myCompany.RepairAgency;


import com.myCompany.RepairAgency.model.ModelManager;
import com.myCompany.RepairAgency.model.entity.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ModelManager modelManager =new ModelManager();

        User userPetrov= modelManager.getUser(1);
        System.out.println(userPetrov.getLogin());

        ArrayList<User> statuses= modelManager.getAllUsers();
        statuses.forEach(a -> System.out.println(a.getId() + " " + a.getLogin()));
    }

}