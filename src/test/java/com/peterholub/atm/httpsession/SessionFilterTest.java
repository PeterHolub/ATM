package com.peterholub.atm.httpsession;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;
import com.peterholub.atm.httpsession.tomcatsessionmanager.CacheSession;
import org.apache.catalina.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.when;

public class SessionFilterTest {

    @Mock
    private CacheManager cacheManager;
    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    HttpServletResponse httpServletResponse;
    @Mock
    FilterChain filterChain;
    @Mock
    HttpSession httpSession;
    @InjectMocks
    SessionFilter sessionFilter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sessionFilter = new SessionFilter(cacheManager);
    }


    @Test
    public void doFilter() throws IOException, ServletException {

        String sessionId = "CE5BD3FCC4A1C601DAC590B26D9A3818";
        String attributeName = "testAttribute";
        String attributeValue = "testAttributeValue";
        Enumeration<String> attributeNames;
        Vector<String> arrayList = new Vector<>();
        arrayList.add(attributeName);
        attributeNames = arrayList.elements();


        when(httpServletRequest.getSession(true)).thenReturn(httpSession);

        when(httpSession.getId()).thenReturn(sessionId);
        when(httpSession.getAttributeNames()).thenReturn(attributeNames);
        when(httpSession.getAttribute(attributeName)).thenReturn(attributeValue);

        sessionFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
        Mockito.verify(cacheManager, times(1)).put(sessionId, attributeName, attributeValue);

    }

}