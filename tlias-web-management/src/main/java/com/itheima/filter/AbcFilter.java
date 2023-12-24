package com.itheima.filter;

import jakarta.servlet.*;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") //当前过滤器需要拦截的路径

public class AbcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Abc拦截到了请求...放行前逻辑");
//        放行
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Abc拦截到了请求...放行后逻辑");
    }
}
