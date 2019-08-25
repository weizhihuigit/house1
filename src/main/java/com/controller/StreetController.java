package com.controller;


import com.github.pagehelper.PageInfo;
import com.pojo.Street;
import com.service.StreetService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class StreetController {
    @Autowired
    private StreetService typeService;
    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public HashMap<String,Object> getDataByPage(Integer did, Page page){
        PageInfo<Street> pageInfo=typeService.getStreetByDistrict(did,page);
        //为了满足datagrid控件实现分，必需返回total键、rows键
        HashMap<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addDialogStreet")
    @ResponseBody
    public String addDialog(Street street){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.addDialog(street);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //通过主键查询单 条
    @RequestMapping("/getOneStreet")
    @ResponseBody
    public Street getStreet(Integer id){
        return typeService.getStreet(id);
    }
    //修改
    @RequestMapping("/updateStreet")
    @ResponseBody
    public String updateStreet(Street street){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.updateStreet(street);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //删除
    @RequestMapping("/delStreet")
    @ResponseBody
    public String delStreet(Integer id){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.delStreet(id);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //批量删除区域
    //delMoreStreet?id=1&id=2&id=3  = public String delStreet(Integer []id)
    @RequestMapping("/delMoreStreet")  //1,2,3
    @ResponseBody
    public String delStreet(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=this.typeService.delMoreStreet(is);
        return "{\"result\":"+temp+"}";
    }
}
