package com.myCompany.RepairAgency.servlet.filter;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncodingFilterTest {

    @Test
    void doFilter() throws ServletException, IOException {
        EncodingFilter filterUnderTest = new EncodingFilter();
        MockFilterChain mockChain = new MockFilterChain();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterConfig filterConfig = new MockFilterConfig();

        filterConfig.addInitParameter("encoding", "UTF-8");

        filterUnderTest.init(filterConfig);
        filterUnderTest.doFilter(request, response, mockChain);
        filterUnderTest.destroy();

        assertEquals("UTF-8", request.getCharacterEncoding());
    }
}