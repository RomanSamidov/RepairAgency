package com.myCompany.RepairAgency.servlet;

import java.util.ResourceBundle;

public class PathFactory {

    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("config");
//            ResourceBundle.getBundle("LocalStrings",  new Locale("en_US"));
//    ResourceBundle rb = ResourceBundle.getBundle("LocalStrings",  req.getLocale());


    private PathFactory() {
    }

    public static Path getPath(String key) {
        return new Path(key.startsWith("path.page.redirect."), resourceBundle.getString(key));
//        return resourceBundle.getString(key);
    }
}