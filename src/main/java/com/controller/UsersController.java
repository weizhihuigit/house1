package com.controller;


import com.github.pagehelper.PageInfo;
import com.pojo.Users;
import com.service.UsersService;
import com.util.UserConditioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("/users")
    @ResponseBody
    public HashMap<String,Object> getDataByPage(UserConditioin conditioin){
        //1.查询所有的学生
        PageInfo<Users> pageInfo=usersService.getUsersByPage(conditioin);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addDialogUsers")
    @ResponseBody
    public String addDialog(Users district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=usersService.addDialog(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //通过主键查询单 条
    @RequestMapping("/getOneUsers")
    @ResponseBody
    public Users getUsers(Integer id){
        return usersService.getUsers(id);
    }
    //修改
    @RequestMapping("/updateUsers")
    @ResponseBody
    public String updateUsers(Users district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=usersService.updateUsers(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //删除
    @RequestMapping("/delUsers")
    @ResponseBody
    public String delUsers(Integer id){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=usersService.delUsers(id);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //批量删除区域
    //delMoreUsers?id=1&id=2&id=3  = public String delUsers(Integer []id)
    @RequestMapping("/delMoreUsers")  //1,2,3
    @ResponseBody
    public String delUsers(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=this.usersService.delMoreUsers(is);
        return "{\"result\":"+temp+"}";
    }
    //注册

}
