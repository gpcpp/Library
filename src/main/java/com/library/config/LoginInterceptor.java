package com.library.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----执行前-----");
        HttpSession session = request.getSession();
        //如果是在登录页面就放行
        if (request.getRequestURI().contains("index")){
            return true;
        }

        if (request.getRequestURI().contains("login")){
            return true;
        }
        //第一次登录,也是没有session的:
        if (session.getAttribute("admin")!=null){
            return true;
        }
        if (session.getAttribute("readercard")!=null)
        {
            return true;
        }

        //如果没有登录就转发到的登录页
        request.getRequestDispatcher("index.html").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
