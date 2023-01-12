package com.myCompany.RepairAgency.servlet.util;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.*;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class ForTablesTest {
    //        HttpServletRequest request = mock(HttpServletRequest.class);
    //        when(request.getSession()).thenReturn(session);
    private MockHttpServletRequest request;
    private MockHttpSession session;
    @Test
    void initSkipQuantity() {
        String tableName = "Test";

        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);

        session.setAttribute("skip" + tableName, 0);
        session.setAttribute("quantity" + tableName, 5);
        session.setAttribute("numberOf" + tableName, 50L);


        assertArrayEquals( new int[]{0,5},
                ForTables.initSkipQuantity(tableName, 100L, request));
        assertEquals( 0,
                session.getAttribute("skip" + tableName));
        assertEquals( 100L,
                session.getAttribute("numberOf" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("skip" + tableName);
        goal.add("numberOf" + tableName);
        goal.add("quantity" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

    @Test
    void updateSkipQuantity() {
        String tableName = "Test";

        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);

        request.setParameter("quantity" + tableName, String.valueOf(-10));

        ForTables.updateSkipQuantity(tableName,  request);

        assertEquals( 0,
                session.getAttribute("skip" + tableName));
        assertEquals( 5,
                session.getAttribute("quantity" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("skip" + tableName);
        goal.add("quantity" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
///////////////
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);

        request.setParameter("skip" + tableName, String.valueOf(-10));

        ForTables.updateSkipQuantity(tableName,  request);

        assertEquals( 0,
                session.getAttribute("skip" + tableName));

        inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
         goal = new ArrayList<>();
        goal.add("skip" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

    @Test
    void updatePagesForJSP() {
        String tableName = "Test";

        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);

        ForTables.updatePagesForJSP(5,0,100,tableName,  request);

        assertEquals( 0,
                session.getAttribute("nowPage" + tableName));
        assertEquals( 5,
                session.getAttribute("nowQuantity" + tableName));
        assertEquals( new ArrayList<>(
                Arrays.asList( 0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95)),
                session.getAttribute("listPages" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("listPages" + tableName);
        goal.add("nowQuantity" + tableName);
        goal.add("nowPage" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }
}