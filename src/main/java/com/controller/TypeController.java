package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Type;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/type")
    @ResponseBody
    public HashMap<String,Object> getDataByPage(int page, int rows){
        //1.查询所有的学生
        PageInfo<Type> pageInfo=typeService.getTypeByPage(page,rows);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addDialogType")
    @ResponseBody
    public String addDialog(Type district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.addDialog(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //通过主键查询单 条
    @RequestMapping("/getOneType")
    @ResponseBody
    public Type getType(Integer id){
        return typeService.getType(id);
    }
    //修改
    @RequestMapping("/updateType")
    @ResponseBody
    public String updateType(Type district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.updateType(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //删除
    @RequestMapping("/delType")
    @ResponseBody
    public String delType(Integer id){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.delType(id);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //批量删除区域
    //delMoreType?id=1&id=2&id=3  = public String delType(Integer []id)
    @RequestMapping("/delMoreType")  //1,2,3
    @ResponseBody
    public String delType(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=this.typeService.delMoreType(is);
        return "{\"result\":"+temp+"}";
    }
}
