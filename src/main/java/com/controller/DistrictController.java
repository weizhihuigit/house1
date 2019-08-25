package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.District;
import com.service.ServiceDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private ServiceDistrict serviceDistrict;
@RequestMapping("/district")
    @ResponseBody
public HashMap<String,Object> getDataByPage(int page, int rows){
    //1.查询所有的学生
    PageInfo<District> pageInfo=serviceDistrict.getDistrictByPage(page,rows);
    //为了满足datagrid控件实现分，必需返回total键、rows键
    HashMap<String,Object> map=new HashMap<>();
    map.put("total",pageInfo.getTotal());
    map.put("rows",pageInfo.getList());
    return map;
}
@RequestMapping("/addDialog")
    @ResponseBody
    public String addDialog(District district){
    int temp=-1;
    try{
        //调用业务实现添加
        temp=serviceDistrict.addDialog(district);
        //传统实现:跳转到视图
        //返回数据
    }catch (Exception ex){
        ex.printStackTrace(); //都会选择记录日志
    }
    return "{\"result\":"+temp+"}";
}
    //通过主键查询单 条
    @RequestMapping("/getOneDistrict")
    @ResponseBody
    public District getDistrict(Integer id){
        return serviceDistrict.getDistrict(id);
    }
    //修改
    @RequestMapping("/updateDistrict")
    @ResponseBody
    public String updateDistrict(District district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=serviceDistrict.updateDistrict(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //删除
    @RequestMapping("/delDistrict")
    @ResponseBody
    public String delDistrict(Integer id){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=serviceDistrict.delDistrict(id);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //批量删除区域
    //delMoreDistrict?id=1&id=2&id=3  = public String delDistrict(Integer []id)
    @RequestMapping("/delMoreDistrict")  //1,2,3
    @ResponseBody
    public String delDistrict(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=this.serviceDistrict.delMoreDistrict(is);
        return "{\"result\":"+temp+"}";

    }
}
