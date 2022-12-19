package com.myCompany.RepairAgency.servlet;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private final static ResourceBundle resourceBundle =
        ResourceBundle.getBundle("config");
//            ResourceBundle.getBundle("LocalStrings",  new Locale("en_US"));
//    ResourceBundle rb = ResourceBundle.getBundle("LocalStrings",  req.getLocale());


    private ConfigurationManager() { }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}