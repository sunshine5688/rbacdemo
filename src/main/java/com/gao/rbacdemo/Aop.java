package com.gao.rbacdemo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gao.entty.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class Aop extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //使用匿名内部类实现接口
        HandlerInterceptor handlerInterceptor=new HandlerInterceptor() {
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
                System.out.println("拦截器********************************************");
                String url = request.getRequestURI();
                System.out.println("请求的url:" + url);
                if(url.contains("index")||url.contains("login")||url.contains("aoperr")|| url.contains("Dmenu")){
                    System.out.println("通过了拦截器*************");
                    return true;
                }
                User user = (User) request.getSession().getAttribute("user");
                if(null == user){
                    request.setAttribute("errormsg","系统未登陆");
                    request.getRequestDispatcher( "/aoperr" ).forward( request,response );
                    return false;
                }
                if("admin".equals(user.getName())){
                    System.out.println("admin用戶，有全部权限！");
                    return true;
                }

                List roles = user.getRoles();
                if(!roles.contains(url.substring(url.lastIndexOf("/") + 1))){
                    System.out.println("没有权限。。。");
                    request.setAttribute("errormsg","没有权限！！！");
                    request.getRequestDispatcher( "/aoperr" ).forward( request,response );
                    return false;
                }

                return true;
            }
            
            public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                    throws Exception {
            }
            
            public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
                    throws Exception {
            }
        };
        //配置拦截路径
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}