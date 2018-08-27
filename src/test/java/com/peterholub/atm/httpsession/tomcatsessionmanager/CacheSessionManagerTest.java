package com.peterholub.atm.httpsession.tomcatsessionmanager;

import org.junit.*;
import static org.junit.Assert.*;

public class CacheSessionManagerTest {

    private CacheSessionManager cacheSessionManager;

    @Before
    public void setUp() {

        cacheSessionManager = new CacheSessionManager(); }

        @Test
    public void getNewSession() {

        assertTrue(cacheSessionManager.getNewSession() instanceof CacheSession);
    }
}