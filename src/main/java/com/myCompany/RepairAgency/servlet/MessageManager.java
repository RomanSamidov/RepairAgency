package com.myCompany.RepairAgency.servlet;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle =
        ResourceBundle.getBundle("LocalStrings",  new Locale("en_US"));

    private MessageManager() { }

    public static String getProperty(String key) {
            return resourceBundle.getString(key);
        }

}