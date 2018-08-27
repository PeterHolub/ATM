package com.peterholub.atm.httpsession.tomcatsessionmanager;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;
import org.apache.catalina.*;
import org.junit.*;
import org.mockito.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CacheSessionTest {
    @Mock
    CacheManager cacheManager;
    @InjectMocks
    CacheSession cacheSession;
    @Mock
    private Manager manager;
    @Mock
    Context context;

    //Creating all needed values for test process
    private String attributeName;
    private String attributeValue;
    private String sessionId;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        //Initialize all values
        cacheSession = new CacheSession(manager, cacheManager);
        attributeName = "testAttribute";
        attributeValue = "testAttributeValue";
        sessionId = "CE5BD3FCC4A1C601DAC590B26D9A3818";
        Object[] applicationEventListeners = new Object[1];

        //creating session with all mocked instances
        when(manager.getContext()).thenReturn(context);
        when(context.getApplicationEventListeners()).thenReturn(applicationEventListeners);

        //making session valid an assigning session id
        cacheSession.setValid(true);
        cacheSession.setId(sessionId);
    }

    @Test
    public void setAttribute() {

        //perform test method
        cacheSession.setAttribute(attributeName, attributeValue);
        //verifying that Cache Manager method was invoke correctly
        Mockito.verify(cacheManager, times(1)).put(sessionId, attributeName, attributeValue);
    }

    @Test
    public void removeAttribute() {

        //perform test method
        cacheSession.removeAttribute(attributeName);
        //verifying that Cache Manager method was invoke correctly
        verify(cacheManager, times(1)).remove(sessionId, attributeName);
    }

    @Test
    public void getAttribute() {

        //preparing returning value for mocked method
        when(cacheManager.get(sessionId, attributeName)).thenReturn(attributeValue);

        // assert that value is the same after method invoke
        assertEquals(attributeValue, cacheSession.getAttribute(attributeName));

        //verifying that Cache Manager method was invoke correctly
        verify(cacheManager, times(1)).get(sessionId, attributeName);
    }

    @Test
    public void getAttributeNames() {

        //creating returning value for test method
        Vector<String> attribute = new Vector<>();
        attribute.add(attributeName);
        Enumeration<String> attributeNames = attribute.elements();

        //preparing returning value for mocked method
        when(cacheManager.getAttributeNames(sessionId)).thenReturn(attributeNames);

        // assert that value is the same after method invoke
        assertEquals(attributeNames, cacheSession.getAttributeNames());

        //verifying that Cache Manager method was invoke correctly
        verify(cacheManager, times(1)).getAttributeNames(sessionId);
    }
}