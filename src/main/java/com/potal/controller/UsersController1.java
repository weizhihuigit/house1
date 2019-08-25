package com.potal.controller;

import com.pojo.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class UsersController1 {
    @Autowired
    private UsersService usersService;
    //注册
    @RequestMapping("/reg")
    public String reg(Users users){
        int i = usersService.regUser(users);
        if (i>0)
            return "login";
        else
            return "regs";
    }
    //判断姓名是否存在
    @RequestMapping("/checkUserName")
    @ResponseBody
    public Map<String,Object> checkUserName(String username) {
        int temp = usersService.isUserNameExists(username);
        //返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("result", temp);
        return map;
    }
    //实现登陆
    @RequestMapping("/login")
    public String checkUserName(String username, String password, HttpSession session){
        Users login = usersService.login(username, password);
        if(login==null){
            return "login";
        }else{
            //注意:只要登入，必需使用session保存登入人的信息或者cookie保存
            //session保存的信息在服务器与浏览共存
            session.setAttribute("userInfo",login);
            //设置保存的时间
            session.setMaxInactiveInterval(600);  //秒
            return "redirect:getHouse";

    }
    }
}
