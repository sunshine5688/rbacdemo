package com.gao.rbacdemo;

import com.gao.dao.CommonMapper;
import com.gao.entty.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String[] roles = request.getParameterValues("roles");
        if(null != roles) {
            //        验证其中角色是否存在互斥
            for (String role : roles) {
                HashMap map1 = new HashMap();
                map1.put("role_id",role);
                for (String role1 : roles) {
                    map1.put("exclude_role_id",role1);
                    Object object = commonMapper.selectExclude_role(map1);
                    if(null != object){
                        String role11 = commonMapper.selectRolename(role);
                        String role22 = commonMapper.selectRolename(role1);
                        request.setAttribute("errormsg","角色：" + role11 + " 与 "
                                + "角色：" + role22 + " 冲突");
                        return "aoperror";
                    }
                }
            }
            for (String role : roles) {
                HashMap map1 = new HashMap();
                map1.put("exclude_role_id",role);
                for (String role1 : roles) {
                    map1.put("role_id",role1);
                    Object object = commonMapper.selectExclude_role(map1);
                    if(null != object){
                        String role11 = commonMapper.selectRolename(role);
                        String role22 = commonMapper.selectRolename(role1);
                        request.setAttribute("errormsg","角色：" + role11 + " 与 "
                                + "角色：" + role22 + " 冲突");
                        return "aoperror";
                    }
                }
            }
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
        // 角色组
        List roleList = commonMapper.selectRole();
        request.setAttribute("roleList",roleList);
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
        if(null != menus) {
            for (String menu : menus) {
                map.put("menu_id", menu);
                commonMapper.insertRole_Menu(map);
            }
        }
        
        String[] roles = request.getParameterValues("roles");
        if(null != roles) {
            for (String role : roles) {
                map.put("exclude_role_id", role);
                commonMapper.insertExclude_role(map);
            }
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
        request.setAttribute("message",request.getRequestURI());

        return "actionMenus";
    }
}
