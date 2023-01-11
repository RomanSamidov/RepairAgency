package com.myCompany.RepairAgency.servlet.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ForTablesTest {

    @Test
    void initSkipQuantity() {
        String tableName = "test";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("skip" + tableName)).thenReturn(0);
        when(session.getAttribute("quantity" + tableName)).thenReturn(5);
        when(session.getAttribute("numberOf" + tableName)).thenReturn(50);

        when(request.getSession()).thenReturn(session);

        assertArrayEquals( new int[]{0,5},
                ForTables.initSkipQuantity("Test", 100, request));
    }

    @Test
    void updateSkipQuantity() {
    }

    @Test
    void updatePagesForJSP() {
    }
}