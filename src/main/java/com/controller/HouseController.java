package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.House;
import com.service.HouseService;
import com.util.HouseCondition;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HouseController {
    @Autowired
    private HouseService houseService;
    //查询未审核
    @RequestMapping("/getNoPassHouse")
    @ResponseBody
    public Map<String,Object>getNoPassHouse(Page page){
        PageInfo<House> pageInfo=houseService.getHouseByIsPass(page,0); //0未审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //修改审核的状态:通过审核
    @RequestMapping("/goYesPassHouse")
    @ResponseBody
    public Map<String,Object> goYesPassHouse(String id){  //page只为接收页码
        int temp=houseService.PassHouse(id,1);//1通过审核
        //封装返回的数据
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;
    }
    //查询以审核
    @RequestMapping("/getYesPassHouse")
    @ResponseBody
    public Map<String,Object>getYesPassHouse(Page page){
        PageInfo<House> pageInfo=houseService.getHouseByIsPass(page,1); //0未审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //修改审核的状态:通过审核
    @RequestMapping("/goNoPassHouse")
    @ResponseBody
    public Map<String,Object> goNoPassHouse(String id){  //page只为接收页码
        int temp=houseService.PassHouse(id,0);//1通过审核
        //封装返回的数据
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;
    }
    //显示单条出租房信息
    @RequestMapping("/searchHouse1")
    @ResponseBody
    public Map<String,Object> searchHouse(HouseCondition condition){
        //获取出租房信息
        PageInfo<House> pageInfo = houseService.getHouseBySearch(condition);
        HashMap<String, Object> map = new HashMap<>();
        //将信息填充到作用域
        map.put("condition",condition);
        return map;
    }
}
