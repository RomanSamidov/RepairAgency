package com.myCompany.RepairAgency.servlet;

import java.util.ResourceBundle;

public class PathFactory {

    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("paths");

    private PathFactory() {
    }

    public static Path getPath(String key) {
        return new Path(key.startsWith("path.page.redirect."), resourceBundle.getString(key));
    }
}