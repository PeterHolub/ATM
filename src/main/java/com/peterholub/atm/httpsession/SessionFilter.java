package com.peterholub.atm.httpsession;

import com.peterholub.atm.httpsession.cachemanager.CacheManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * Session manager based on Filter
 * Easy to Inject on any platform
 * Server independent
 * Methods doFilter can implement saving to storage , but for update and delete - more complicated logic needed
 * easy to connect in web.xml by
 * <filter-mapping>
 * <filter-name>SessionFilter</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 */

public class SessionFilter implements Filter {

    private CacheManager cacheManager;

    public SessionFilter(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Saves attributes to storage by Cache Manager instance each time when request/response pair is passed through the chain due to a client request for a resource at the end of the chain.
     *
     * @param request  the request to pass along the chain.
     * @param response the response to pass along the chain.
     * @param chain    instance of
     * @throws ServletException throws ServletException
     * @throws IOException      throws IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

        chain.doFilter(req, res);

        Enumeration enums = session.getAttributeNames();

        while (enums.hasMoreElements()) {

            String sessionId = session.getId();
            String attributeName = (String) enums.nextElement();
            Object attribute = session.getAttribute(attributeName);

            cacheManager.put(sessionId, attributeName, attribute);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }
}