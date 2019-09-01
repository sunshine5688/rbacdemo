package com.gao.rbacdemo;

import dao.CommonMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainControl {

    @Autowired
    CommonMapper commonMapper;

    @RequestMapping("/index")
    public String index(Object obj) {
        return "index";
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        request.setAttribute("adduser","true");
        return "menu";
    }

    @RequestMapping("/init_adduser")
    public String adduser(HttpServletRequest request) {
        HashMap map = new HashMap();
        map.put("id","2");
        System.out.println(commonMapper.selectUser(map));
        List groupList =  commonMapper.selectGroup();
        request.setAttribute("groupList",groupList);
        return "adduser";
    }
}
