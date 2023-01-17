package com.myCompany.RepairAgency.servlet;

import com.myCompany.RepairAgency.Constants;

import java.util.ResourceBundle;

public class PathFactory {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle(Constants.PATHS_BUNDLE);

    private PathFactory() {
    }

    public static Path getPath(String key) {
        return new Path(key.startsWith("path.page.redirect."), resourceBundle.getString(key));
    }
    public static Path getPath(String key, boolean isDownload) {
        return new Path(key.startsWith("path.page.redirect."), isDownload, resourceBundle.getString(key));
    }
}