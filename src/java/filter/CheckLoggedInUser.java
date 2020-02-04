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
import bean.ShoppingCart;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@WebFilter(filterName = "CheckLoggedInUser", urlPatterns
        = {
            "/*"
        })
public class CheckLoggedInUser implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    @Inject
    ShoppingCart shoppingCart;

    public CheckLoggedInUser() {
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
        String loginURI = request.getContextPath() + "/faces/Login.xhtml";

        //avoid cache of webpage - improve security
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        boolean loggedIn = shoppingCart.getUser() != null;

        String logoutURI = request.getContextPath() + "/faces/Logout.xhtml";

        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean logoutRequest = request.getRequestURI().equals(logoutURI);
        String indexURI = request.getContextPath() + "/faces/index.xhtml";

        //especially usefull when the user use the Login or register page
        //after using it the User will redirected to the webpage that was using before
        //of request the login or register page
        if (request.getRequestURI().contains(".xhtml")) {
            String[] refArr = request.getRequestURI().split("/");
            String lastRef = refArr[refArr.length - 1];
            if (!lastRef.equals(shoppingCart.getCurrentURI())) {
                shoppingCart.setPreviousURI(shoppingCart.getCurrentURI());
                shoppingCart.setCurrentURI(lastRef);

            }

        }
//        System.out.println("Current url: " + shoppingCart.getCurrentURI());
//        System.out.println("Preious url: " + shoppingCart.getPreviousURI());

        if (loggedIn && logoutRequest) {
            shoppingCart.logout();
            response.sendRedirect(indexURI);
        } else if (loggedIn && loginRequest) {
            response.sendRedirect(indexURI);

        } else {
            chain.doFilter(request, response);

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
    public void init(FilterConfig filterConfig
    ) {
        this.filterConfig = filterConfig;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
