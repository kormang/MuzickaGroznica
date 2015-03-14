package net.etfbl.muzickagroznica.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NoCacheFilter
 */
public class NoCacheFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NoCacheFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        System.err.println("contextPath: " + httpReq.getContextPath());
        System.err.println("requestURI: " + httpReq.getRequestURI());
        System.err.println("requestURL: " + httpReq.getRequestURL());
        
        //if (!httpReq.getRequestURI().startsWith(httpReq.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { 
            httpRes.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpRes.setHeader("Pragma", "no-cache");
            httpRes.setDateHeader("Expires", 0);
        //}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
