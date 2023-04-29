package com.myCompany.RepairAgency.servlet;

import java.util.HashMap;

/**
 * Class for presentation path for Redirect or Forward, which was returned by command, or indicate that command ends with download.
 */
public class Path {
    public final boolean isRedirect;
    public final String path;
    private final boolean isDownload;
    private final HashMap<String, String> parameters = new HashMap<>();

    public Path(boolean redirect, boolean download, String path) {
        this.isRedirect = redirect;
        this.isDownload = download;
        this.path = path;
    }

    public Path(boolean redirect, String path) {
        this.isRedirect = redirect;
        this.isDownload = false;
        this.path = path;
    }

    public Path(String path) {
        this.isRedirect = false;
        this.isDownload = false;
        this.path = path;
    }

    @Override
    public String toString() {
        if (parameters.isEmpty()) return path;

        StringBuilder builder = new StringBuilder(path);
        builder.append("?");
        parameters.forEach((key, value) -> builder.append(key).append("=").append(value).append("&"));
        return builder.toString();
    }

    public boolean isBlank() {
        return path == null || path.isBlank();
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    public boolean isDownload() {
        return isDownload;
    }

}
