package com.dxy.zhbean.jwtauth.common;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.dxy.zhbean.jwtauth.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/13
 */
@WebFilter(filterName = "refreshFilter",urlPatterns = "/*")
public class RefreshTokenFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //过滤请求 token即将过期的在响应头里面带回新的token
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("x-access-token");
        String requestURI = req.getRequestURI();
        if (StringUtils.isBlank(token)) {
            //login 不带token
            chain.doFilter(request, response);
        } else {
            String tk = JwtUtils.refreshToken(token, 60 * 60 * 1000);
            if (tk.equals(token) || requestURI.contains("logout")) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse resp = (HttpServletResponse) response;
                //跨域请求要设置Access-Control-Expose-Headers
                resp.setHeader("Access-Control-Expose-Headers", "refresh-token");
                resp.setHeader("refresh-token", tk);
                chain.doFilter(request, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
