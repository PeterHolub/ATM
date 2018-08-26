package com.peterholub.atm.httpsession.cachemanager;

import java.util.*;

/**
 * Cache manager interface
 * Provides methods to work with storage
 **/
public interface CacheManager {

    /**
     * Saving value to storage
     *
     * @param sessionId      - id of session to save
     * @param attributeName  - attribute name
     * @param attributeValue - value of attribute to save
     */
    void put(String sessionId, String attributeName, Object attributeValue);

    /**
     * Updates attribute value
     *
     * @param sessionId      - id of session to replace
     * @param attributeName  - attribute name
     * @param attributeValue - value of attribute to replace
     */
    void replace(String sessionId, String attributeName, Object attributeValue);

    /**
     * Getting value by session id & attribute name
     *
     * @param sessionId     - id of session
     * @param attributeName - attribute name to get from storage
     */
    Optional get(String sessionId, String attributeName);

    /**
     * Removing value by session id & attribute name
     *
     * @param sessionId     - id of session to remove
     * @param attributeName - attribute name
     */
    void remove(String sessionId, String attributeName);

    /**
     * Getting all attributes names in session from storage
     *
     * @param sessionId - id of session
     */
    Enumeration<String> getAttributeNames(String sessionId);


    /**
     * Cleaning storage after session timeout
     *
     * @param sessionTimeout - sets time after what session will be deleted from storage
     */
    void timeToLeave(long sessionTimeout);


}
