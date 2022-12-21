package com.myCompany.RepairAgency.servlet;

public class Path {
    public final boolean isRedirect;
    public final String path;

    public Path(boolean redirect, String path) {
        this.isRedirect = redirect;
        this.path = path;
    }
    public Path(String path) {
        this.isRedirect = false;
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }

    public boolean isBlank() {
        return path == null || path.isBlank();
    }
}
