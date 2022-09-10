package com.zzteam.blogcenter.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//
///**
// * 做登录检查
// * 1，配置好拦截器要拦截哪些请求
// * 2，把这些配置放在容器中
// *
// */
//@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
//    /**
//     * 目标方法执行之前
//     * @param request
//     * @param response
//     * @param handler
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String requestURI = request.getRequestURI();
//        log.info("拦截的请求路径是{}",requestURI);
//
//        //登录检查逻辑
//        HttpSession session = request.getSession();
//        System.out.println("输出session为：");
//        System.out.println(session.toString());
//        Object loginUser = session.getAttribute("loginUser");//看session有没有放已经登录的用户
//        if (loginUser != null){
//            //只要当前有用户登录 返回true
//            return true;//即放行
//        }
//        //否则拦截住，即未登录，跳转到登录页
////        session.setAttribute();
//        log.info("跳转到登录页");
//        response.sendRedirect("/user/login");
//        return false;
//    }
//
//    /**
//     * 目标方法执行完成以后
//     * @param request
//     * @param response
//     * @param handler
//     * @param modelAndView
//     * @throws Exception
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    /**
//     * 页面渲染以后
//     * @param request
//     * @param response
//     * @param handler
//     * @param ex
//     * @throws Exception
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
