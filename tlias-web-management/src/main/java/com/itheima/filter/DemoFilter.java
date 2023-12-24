package com.itheima.filter;

import jakarta.servlet.*;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") //当前过滤器需要拦截的路径
public class DemoFilter implements Filter {

    @Override //初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求...放行前逻辑");
//        放行
        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("拦截到了请求...放行后逻辑");
    }

    @Override  //销毁方法，只调用一次
    public void destroy() {
        System.out.println("销毁过滤器");
        Filter.super.destroy();
    }
}
