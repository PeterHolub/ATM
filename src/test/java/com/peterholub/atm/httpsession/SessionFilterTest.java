package com.peterholub.atm.httpsession;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.times;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import static org.mockito.Mockito.*;

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

        //Creating all needed values for test process
        String sessionId = "CE5BD3FCC4A1C601DAC590B26D9A3818";
        String attributeName = "testAttribute";
        String attributeValue = "testAttributeValue";
        Enumeration<String> attributeNames;
        Vector<String> arrayList = new Vector<>();
        arrayList.add(attributeName);
        attributeNames = arrayList.elements();

       //performing returning of values from mocked instances
        when(httpServletRequest.getSession(true)).thenReturn(httpSession);

        when(httpSession.getId()).thenReturn(sessionId);

        when(httpSession.getAttributeNames()).thenReturn(attributeNames);

        when(httpSession.getAttribute(attributeName)).thenReturn(attributeValue);

        //perform test method
        sessionFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        //verifying that Cache Manager method was invoke
        verify(cacheManager, times(1)).put(sessionId, attributeName, attributeValue);

    }

}