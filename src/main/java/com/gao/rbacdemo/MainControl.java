package com.gao.rbacdemo;

import dao.CommonMapper;
import entty.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/view")
public class MainControl {

    @Autowired
    CommonMapper commonMapper;

    @RequestMapping("/init_adduser")
    public String init_adduser(HttpServletRequest request) {
        // 用户组
        List groupList =  commonMapper.selectGroup();
        request.setAttribute("groupList",groupList);
        // 角色组
        List roleList = commonMapper.selectRole();
        request.setAttribute("roleList",roleList);
        return "adduser";
    }

    @RequestMapping("/adduser")
    @Transactional
    public String adduser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HashMap map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        commonMapper.insertUser(map);
        String userid = commonMapper.selectUserId(map);
        map.put("userid" , userid);
        String[] groups = request.getParameterValues("groups");
        if(null != groups) {
            for (String group : groups) {
                map.put("groupid", group);
                commonMapper.insertUser_group(map);
            }
        }

        String[] roles = request.getParameterValues("roles");
        if(null != roles) {
            for (String role : roles) {
                map.put("roleid", role);
                commonMapper.insertUser_role(map);
            }
        }
        return "success";
    }

    @RequestMapping("/init_addrole")
    public String init_addrole(HttpServletRequest request) {
        List menuList = commonMapper.selectMenu();
        request.setAttribute("menuList",menuList);
        return "addrole";
    }
    @RequestMapping("/addrole")
    @Transactional
    public String addrole(HttpServletRequest request) {
        HashMap map = new HashMap();
        String rolename = request.getParameter("rolename");
        map.put("name",rolename);
        Object ob = commonMapper.insertRole(map);
        String roleid = commonMapper.selectRoleId(map);
        map.put("role_id",roleid);


        String[] menus = request.getParameterValues("menus");
        System.out.println(rolename);
        for (String menu : menus) {
            map.put("menu_id",menu);
            commonMapper.insertRole_Menu(map);
        }
        return "success";
    }

    @RequestMapping("/Dmenu")
    public String dmenu(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        for (Object o : user.getRoles()) {
            request.setAttribute(o + "","true");
        }
        return "menu";
    }

    @RequestMapping("/menu*")
    public String menu(HttpServletRequest request) {
        return "error";
    }
}
