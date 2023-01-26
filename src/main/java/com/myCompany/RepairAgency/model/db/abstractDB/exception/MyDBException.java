package com.myCompany.RepairAgency.model.db.abstractDB.exception;


public class MyDBException extends RuntimeException {
    public MyDBException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public MyDBException() {

    }
}
