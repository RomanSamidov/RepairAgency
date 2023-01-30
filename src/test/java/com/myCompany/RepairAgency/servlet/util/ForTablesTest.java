package com.myCompany.RepairAgency.servlet.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class ForTablesTest {
    //        HttpServletRequest request = mock(HttpServletRequest.class);
    //        when(request.getSession()).thenReturn(session);
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpSession session;

    String tableName = "Test";

    @BeforeEach
    public void initMocks() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    void initSkipQuantity1() {

        session.setAttribute("nowPageFor" + tableName, null);
        session.setAttribute("nowQuantityFor" + tableName, 0);
        session.setAttribute("numberOf" + tableName, 50L);


        assertArrayEquals(new int[]{0, 5},
                ForTables.initSkipQuantity(tableName, 0L, request));
        assertEquals(5,
                session.getAttribute("nowQuantityFor" + tableName));
        assertEquals(0L,
                session.getAttribute("numberOf" + tableName));
        assertEquals(1,
                session.getAttribute("nowPageFor" + tableName));
        assertEquals(1,
                request.getAttribute("maxPageFor" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("nowQuantityFor" + tableName);
        goal.add("numberOf" + tableName);
        goal.add("nowPageFor" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

    @Test
    void initSkipQuantity2() {

        session.setAttribute("nowPageFor" + tableName, 10000);
        session.setAttribute("nowQuantityFor" + tableName, 0);
        session.setAttribute("numberOf" + tableName, 50L);


        assertArrayEquals(new int[]{0, 5},
                ForTables.initSkipQuantity(tableName, 0L, request));
        assertEquals(5,
                session.getAttribute("nowQuantityFor" + tableName));
        assertEquals(0L,
                session.getAttribute("numberOf" + tableName));
        assertEquals(1,
                session.getAttribute("nowPageFor" + tableName));
        assertEquals(1,
                request.getAttribute("maxPageFor" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("nowQuantityFor" + tableName);
        goal.add("numberOf" + tableName);
        goal.add("nowPageFor" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

    @Test
    void initSkipQuantity3() {

        session.setAttribute("nowPageFor" + tableName, 10000);
        session.setAttribute("nowQuantityFor" + tableName, 0);


        assertArrayEquals(new int[]{0, 5},
                ForTables.initSkipQuantity(tableName, 0L, request));
        assertEquals(5,
                session.getAttribute("nowQuantityFor" + tableName));
        assertEquals(0L,
                session.getAttribute("numberOf" + tableName));
        assertEquals(1,
                session.getAttribute("nowPageFor" + tableName));
        assertEquals(1,
                request.getAttribute("maxPageFor" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("nowQuantityFor" + tableName);
        goal.add("numberOf" + tableName);
        goal.add("nowPageFor" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

    @Test
    void updateSkipQuantity_1() {

        request.setParameter("newQuantityFor" + tableName, String.valueOf(-10));

        ForTables.updateSkipQuantity(tableName, request);

        assertEquals(5,
                session.getAttribute("nowQuantityFor" + tableName));
        assertEquals(1,
                session.getAttribute("nowPageFor" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("nowQuantityFor" + tableName);
        goal.add("nowPageFor" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

    @Test
    void updateSkipQuantity_2() {

        request.setParameter("goToPageFor" + tableName, String.valueOf(-10));

        ForTables.updateSkipQuantity(tableName, request);

        assertEquals(1,
                session.getAttribute("nowPageFor" + tableName));

        List<String> inSession = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(session.getAttributeNames().asIterator(), Spliterator.ORDERED), false)
                .toList();
        List<String> goal = new ArrayList<>();
        goal.add("nowPageFor" + tableName);

        assertTrue(goal.containsAll(inSession));
        assertTrue(inSession.containsAll(goal));
    }

}