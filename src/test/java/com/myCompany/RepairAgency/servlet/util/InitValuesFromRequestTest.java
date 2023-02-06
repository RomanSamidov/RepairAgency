package com.myCompany.RepairAgency.servlet.util;

import com.myCompany.RepairAgency.Constants;
import com.myCompany.RepairAgency.model.db.abstractDB.repository.entity.iRepairOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InitValuesFromRequestTest {
    @Mock
    MockHttpServletRequest request;
    @Mock
    MockHttpSession session;


    @BeforeEach
    public void initMocks() {
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
        request.setSession(session);
    }

    @Test
    void initCraftsmenIds1() {
        session.setAttribute("userRole", Constants.ROLE.Craftsman);
        session.setAttribute("userId", 1L);
        request.setParameter("newIsUserAllowLetters", "false");

        assertArrayEquals(new long[]{1L}, InitValuesFromRequest.initCraftsmenIds(request));
    }

    @Test
    void initCraftsmenIds2() {
        session.setAttribute("userRole", Constants.ROLE.Admin);

        assertArrayEquals(new long[]{}, InitValuesFromRequest.initCraftsmenIds(request));
    }

    @Test
    void initCraftsmenIds3() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        session.setAttribute("craftsmanIdOrders", new long[]{0L, 2L});

        assertArrayEquals(new long[]{}, InitValuesFromRequest.initCraftsmenIds(request));
    }

    @Test
    void initCraftsmenIds4() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        session.setAttribute("craftsmanIdOrders", new long[]{1L, 2L});

        assertArrayEquals(new long[]{1L, 2L}, InitValuesFromRequest.initCraftsmenIds(request));
    }

    @Test
    void initStatusIds1() {
        session.setAttribute("statusOrders", new long[]{1L, 2L});

        assertArrayEquals(new long[]{1L, 2L}, InitValuesFromRequest.initStatusIds(request));
    }

    @Test
    void initStatusIds2() {
        session.setAttribute("statusOrders", new long[]{0L, 2L});

        assertArrayEquals(new long[]{}, InitValuesFromRequest.initStatusIds(request));
    }

    @Test
    void initStatusIds3() {
        assertArrayEquals(new long[]{}, InitValuesFromRequest.initStatusIds(request));
    }

    @Test
    void initUserId1() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        session.setAttribute("userId", 1L);

        assertEquals(1L, InitValuesFromRequest.initUserId(request));
    }

    @Test
    void initUserId2() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        session.setAttribute("userId", 1L);
        assertEquals(0L, InitValuesFromRequest.initUserId(request));
    }

    @Test
    void initSortType1() {
        assertEquals(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_ASC, InitValuesFromRequest.initSortType(request));

    }

    @Test
    void initSortType2() {
        session.setAttribute("sortTypeOrders", iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_DESC);
        assertEquals(iRepairOrderRepository.SORT_TYPE.ORDER_BY_ID_DESC, InitValuesFromRequest.initSortType(request));
    }

    @Test
    void initRoleId1() {
        session.setAttribute("userRole", Constants.ROLE.Admin);
        assertEquals(0L, InitValuesFromRequest.initRoleId(request));
    }

    @Test
    void initRoleId2() {
        session.setAttribute("userRole", Constants.ROLE.Client);
        assertEquals(4L, InitValuesFromRequest.initRoleId(request));
    }

    @Test
    void initRoleId3() {
        session.setAttribute("roleUsers", Constants.ROLE.Client);
        assertEquals(4L, InitValuesFromRequest.initRoleId(request));
    }

    @Test
    void initRoleId4() {
        session.setAttribute("roleUsers", "ee");
        assertEquals(4L, InitValuesFromRequest.initRoleId(request));
    }

    @Test
    void initGoalId1() {
        request.setParameter("id", "1");
        assertEquals(1L, InitValuesFromRequest.initGoalId(request));
    }

    @Test
    void initGoalId2() {
        request.setParameter("id", "-1");
        assertEquals(0L, InitValuesFromRequest.initGoalId(request));
    }

    @Test
    void initGoalId3() {
        request.setParameter("id", "-a");
        assertEquals(0L, InitValuesFromRequest.initGoalId(request));
    }

    @Test
    void initGoalId4() {
        assertEquals(0L, InitValuesFromRequest.initGoalId(request));
    }
}