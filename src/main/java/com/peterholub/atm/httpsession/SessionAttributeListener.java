package com.peterholub.atm.httpsession;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;

import javax.servlet.http.*;

/**
 * Session manager based on HttpSessionAttributeListener
 * Easy to Inject on any platform
 * Server independent
 * Methods attributeAdded, attributeRemoved, attributeReplaced very easy to implement CRUD operations with storage
 * All methods are invoking each time when values are changing in Http Session
 * easy to connect in web.xml by
 * <listener>
 * <listener-class>SessionAttributeListener</listener-class>
 * </listener>
 */


public class SessionAttributeListener implements HttpSessionAttributeListener {

    private CacheManager cacheManager;

    public SessionAttributeListener(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Saving session attribute
     *
     * @param httpSessionBindingEvent event object with updated session values
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String sessionId = httpSessionBindingEvent.getSession().getId();
        String attributeName = httpSessionBindingEvent.getName();
        Object attributeValue = httpSessionBindingEvent.getValue();
        cacheManager.put(sessionId, attributeName, attributeValue);
    }

    /**
     * Removing session value by sessionId & attributeName
     *
     * @param httpSessionBindingEvent event object with updated session values
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String sessionId = httpSessionBindingEvent.getSession().getId();
        String attributeName = httpSessionBindingEvent.getName();

        cacheManager.remove(sessionId, attributeName);
    }

    /**
     * Updating session attribute
     *
     * @param httpSessionBindingEvent event object with updated session values
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String sessionId = httpSessionBindingEvent.getSession().getId();
        String attributeName = httpSessionBindingEvent.getName();
        Object attributeValue = httpSessionBindingEvent.getValue();

        cacheManager.replace(sessionId, attributeName, attributeValue);
    }
}
