package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 1. 获取请求url
        String url = req.getRequestURI().toString();
        System.out.println("请求的url是：" + url);
        // 2. 判断url是否包含Login，如果包含，放行
        if (url.contains("/login")) {
            filterChain.doFilter(req, resp);
            return;
        }
        // 3. 获取请求头里面的token
        String token = req.getHeader("token");
        // 4. 判断token是否为空，如果不存在，返回错误结果（未登录）
        if (StringUtils.isEmpty(token)) {
            log.info("请求头token为空，返回未登录的信息");

            Result error = Result.error("NOT_LOGIN");
            //手动转换对象 --json ------>阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        // 5. 判断token是否有效，如果解析失败，返回错误结果未登录
        try {
            Claims claims = JwtUtils.parseJWT(token);
            log.info("token解析成功: {}", claims);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("token解析失败，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换对象 --json ------>阿里巴轨迹fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        // 6. 如果token有效，放行
        log.info("token有效，放行");
        filterChain.doFilter(req, resp);
    }
}
