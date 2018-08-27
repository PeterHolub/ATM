package com.peterholub.atm.httpsession;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;
import org.junit.*;
import org.mockito.*;
import org.springframework.mock.web.MockHttpSession;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import static org.mockito.Mockito.*;

public class SessionAttributeListenerTest {

    @Mock
    private CacheManager cacheManager;
    @InjectMocks
    private SessionAttributeListener sessionAttributeListener;
    @Mock
    private ServletContext servletContext;

    private MockHttpSession httpSession;
    //Creating all needed values for test process
    private String sessionId;
    private String attributeName;
    private String attributeValue;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //Initialize all values
        sessionAttributeListener = new SessionAttributeListener(cacheManager);
        sessionId = "CE5BD3FCC4A1C601DAC590B26D9A3818";
        attributeName = "testAttribute";
        attributeValue = "testAttributeValue";

        //Creating http session object with mocked servlet context and adding session id
        httpSession = new MockHttpSession(servletContext, sessionId);
    }

    @Test
    public void attributeAdded() {

        //Creating HttpSessionBindingEvent with attribute values and session object
        HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(httpSession, attributeName, attributeValue);

        //perform test method
        sessionAttributeListener.attributeAdded(httpSessionBindingEvent);

        //verifying that Cache Manager method was invoke correctly
        verify(cacheManager, times(1)).put(sessionId, attributeName, attributeValue);

    }

    @Test
    public void attributeRemoved() {

        //Creating HttpSessionBindingEvent with attribute values and session object
        HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(httpSession, attributeName);

        //perform test method
        sessionAttributeListener.attributeRemoved(httpSessionBindingEvent);

        //verifying that Cache Manager method was invoke correctly
        verify(cacheManager, times(1)).remove(sessionId, attributeName);
    }

    @Test
    public void attributeReplaced() {

        //Creating HttpSessionBindingEvent with attribute values and session object
        HttpSessionBindingEvent httpSessionBindingEvent = new HttpSessionBindingEvent(httpSession, attributeName, attributeValue);

        //perform test method
        sessionAttributeListener.attributeReplaced(httpSessionBindingEvent);

        //verifying that Cache Manager method was invoke correctly
        verify(cacheManager, times(1)).replace(sessionId, attributeName, attributeValue);
    }
}