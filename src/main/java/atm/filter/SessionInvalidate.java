package atm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Invalidates session after redirect to home.jsp
@WebFilter(filterName = "SessionInvalidate",urlPatterns = "/home.jsp")
public class SessionInvalidate implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        session.invalidate();
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
    }
    @Override
    public void destroy() {
    }
}