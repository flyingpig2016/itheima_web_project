package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override //目标资源方法运行前运行，返回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        System.out.println("preHandle ...");
//        return true;
        // 1. 获取请求url
        String url = req.getRequestURI().toString();
        System.out.println("请求的url是：" + url);
        // 2. 判断url是否包含Login，如果包含，放行
        if (url.contains("/login")) {
            return true;
        }
        // 3. 获取请求头里面的token
        String token = req.getHeader("token");
        // 4. 判断token是否为空，如果不存在，返回错误结果（未登录）
        if (StringUtils.isEmpty(token)) {
            log.info("请求头token为空，返回未登录的信息");

            Result error = Result.error("NOT_LOGIN");
            //手动转换对象 --json ------>阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            log.info("未登录，返回错误信息: {}", notLogin);
            resp.getWriter().write(notLogin);
            return false;
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
            return false;
        }

        // 6. 如果token有效，放行
        log.info("token有效，放行");
        return true;
    }

    @Override //目标资源方法运行后运行，返回true：放行，返回false：不放行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("postHandle ...");
    }

    @Override // 视图渲染完毕之后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("afterCompletion ...");
    }
}
