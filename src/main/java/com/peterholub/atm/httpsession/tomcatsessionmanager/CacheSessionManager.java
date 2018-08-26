package com.peterholub.atm.httpsession.tomcatsessionmanager;

import org.apache.catalina.session.*;

/**
 * Session manager based on Tomcat's StandardManager
 * Easy to Inject on any platform
 * Server dependent
 * easy to connect in server.xml by
 * <Context>
 * ...
 * <Manager className="com.infrasafe.ivisitor.httpsession.tomcatsessionmanager.CacheSessionManager"/>
 * ...
 * </Context>
 */

public class CacheSessionManager extends StandardManager {
    /**
     * Overriding method getNewSession for changing a type of session, from StandardSession to our implementation called CacheSession
     * Adding instance of this Session manager to CacheSession constructor
     */

    @Override
    protected StandardSession getNewSession() {
        return new CacheSession(this);
    }


}
















