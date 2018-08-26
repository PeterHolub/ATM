package com.peterholub.atm.httpsession.tomcatsessionmanager;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;
import org.apache.catalina.*;
import org.apache.catalina.session.StandardSession;
import java.util.Enumeration;

/**
 * Cache Manager implementation of the <b>Session</b> interface.
 * All overridden methods are using Cache Manager instance for working with storage
 */

public class CacheSession extends StandardSession {

    private CacheManager cacheManager;

    public CacheSession(Manager manager, CacheManager cacheManager) {
        super(manager);
        this.cacheManager = cacheManager;
    }

    CacheSession(Manager manager) {
        super(manager);
    }

    /**
     * Bind an object to this session, using the specified name.  If an object
     * of the same name is already bound to this session, the object is
     * replaced.
     * <p>
     * After this method executes, and if the object implements
     * <code>HttpSessionBindingListener</code>, the container calls
     * <code>valueBound()</code> on the object.
     *
     * @param name  Name to which the object is bound, cannot be null
     * @param value Object to be bound, cannot be null
     * @throws IllegalArgumentException if an attempt is made to add a
     *                                  non-serializable object in an environment marked distributable.
     * @throws IllegalStateException    if this method is called on an
     *                                  invalidated session
     */
    @Override
    public void setAttribute(String name, Object value) {
        cacheManager.put(getId(), name, value);
        super.setAttribute(name, value);
    }

    /**
     * Remove the object bound with the specified name from this session.  If
     * the session does not have an object bound with this name, this method
     * does nothing.
     * <p>
     * After this method executes, and if the object implements
     * <code>HttpSessionBindingListener</code>, the container calls
     * <code>valueUnbound()</code> on the object.
     *
     * @param name Name of the object to remove from this session.
     * @throws IllegalStateException if this method is called on an
     *                               invalidated session
     */

    @Override
    public void removeAttribute(String name) {
        cacheManager.remove(getId(), name);
        super.removeAttribute(name);
    }

    /**
     * Return the object bound with the specified name in this session, or
     * <code>null</code> if no object is bound with that name.
     *
     * @param name Name of the attribute to be returned
     * @throws IllegalStateException if this method is called on an
     *                               invalidated session
     */

    @Override
    public Object getAttribute(String name) {
        return cacheManager.get(getId(), name);
    }

    /**
     * Return an <code>Enumeration</code> of <code>String</code> objects
     * containing the names of the objects bound to this session.
     *
     * @throws IllegalStateException if this method is called on an
     *                               invalidated session
     */

    @Override
    public Enumeration<String> getAttributeNames() {
        return cacheManager.getAttributeNames(getId());
    }

}
