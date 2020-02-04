package filter;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.AdminUserBean;

@WebFilter(filterName = "CheckLoggedIn", urlPatterns
        = {
            "/faces/admin/*"
        })
public class CheckLoggedIn implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    @Inject
    AdminUserBean adminUserBean;

    public CheckLoggedIn() {
    }

    /*
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        //avoid cache of webpage - improve security
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        String loginURI = request.getContextPath() + "/faces/admin/index.xhtml";
        //String registerURI = request.getContextPath() + "/faces/register.xhtml";
        String loggedURIDefault = "/faces/admin/ViewAllProducts.xhtml";
        String logoutURI = request.getContextPath() + "/faces/admin/Logout.xhtml";

        //adminUserBean = (session != null) ? (AdminUserBean) session.getAttribute("adminUserBean") : null;
        System.out.println("PASSS THIS" + adminUserBean);

        boolean loggedIn = adminUserBean.credentialsAreOK();
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean logoutRequest = request.getRequestURI().equals(logoutURI);
        System.out.println("^^^" + logoutURI);
        // boolean registerRequest = request.getRequestURI().equals(registerURI);

        System.out.println("loggedIn " + loggedIn);
        System.out.println("loginRequest " + loginRequest);
        System.out.println("##" + request.getRequestURI());
        System.out.println("Logoutrequest; " + logoutRequest);
        if (logoutRequest) {
            adminUserBean.clearCredentials();
            //adminUserBean.setUname("");
            response.sendRedirect(loginURI);

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(adminUserBean);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //this.destroy();

        } else if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    /*
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    /*
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
