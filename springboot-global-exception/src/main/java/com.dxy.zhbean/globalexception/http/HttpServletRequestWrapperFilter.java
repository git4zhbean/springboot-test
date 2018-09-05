package com.dxy.zhbean.globalexception.http;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/5
 */
@WebFilter(filterName = "httpServletRequestWrapperFilter")
public class HttpServletRequestWrapperFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest servletRequest = null;
        if (request instanceof HttpServletRequest) {
            servletRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
        }
        if (null == servletRequest) {
            chain.doFilter(request, response);
        }else {
            chain.doFilter(servletRequest, response);
        }

    }

    @Override
    public void destroy() {

    }
}
