package com.gao.rbacdemo;

import dao.CommonMapper;
import entty.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginControl {

    @Autowired
    CommonMapper commonMapper;

    @RequestMapping("/index")
    public String index(Object obj) {
        return "index";
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        HashMap map = new HashMap();
        map.put("username",request.getParameter("username"));
        map.put("password",request.getParameter("password"));
        Map result = commonMapper.selectUser(map);
        if (result == null){
           request.setAttribute("errormsg","账号或者密码错误");
           return "index";
        }
        System.out.println(result);
        User user = new User();

        user.setId(result.get("id") + "");
        user.setName(result.get("username") + "");
        request.getSession().setAttribute("user",user);
        List list = new ArrayList();
        List menuList = new ArrayList();
        if("admin".equals(user.getName())) {
            menuList = commonMapper.selectMenu();
        }else{
            map.put("userid",user.getId());
            menuList = commonMapper.selectMenuUser(map);
        }
        for (Object o : menuList) {
            HashMap menu = (HashMap)o;
            String url = menu.get("url")+ "";
            request.setAttribute(url,"true");
            list.add(url);
            if(url.contains("_")){
                request.setAttribute(url.substring(url.indexOf("_") + 1),"true");
                list.add(url.substring(url.indexOf("_") + 1));
            }
        }
            user.setRoles(list);

        return "menu";
    }
}
