package pcol.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcol.shared.User;

public class AuthorisationFilter implements javax.servlet.Filter {

	public void init(FilterConfig conf) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            String rsrc = request.getRequestURI().substring(request.getContextPath().length());

            HttpSession session = request.getSession(false);

            User user = null;
            if (session != null) {
                user = (User) session.getAttribute(Provider.SESSION_USER);
            }

            Provider authProvider = Provider.getInstance();

            if (!authProvider.isAuthorised(user, rsrc)) {
                if(user == null){
                	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }else{
                	response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
                return;
            }
        }
        chain.doFilter(req, resp);
    }

}