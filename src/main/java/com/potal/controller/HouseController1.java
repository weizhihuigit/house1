package com.potal.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.House;
import com.pojo.Users;
import com.service.HouseService;
import com.util.HouseCondition;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class HouseController1 {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/addHouse")
    public String addHouse(House house, @RequestParam(value="pfile",required = false) CommonsMultipartFile pfile, HttpSession session, Model model){
        try {
            //第一步上传图片
            String path="d:\\images\\";  //存放文件的位置
            //生成唯一文件名
            String oldName=pfile.getOriginalFilename();
            String expname=oldName.substring(oldName.lastIndexOf("."));
            String filename=System.currentTimeMillis()+expname;
            File file=new File(path+filename);
            pfile.transferTo(file);  //上传，保存

            //第二步保存信息到数据库
            //设置主键
            house.setId(System.currentTimeMillis()+"");
            //设置发布出租房的用户
            Users users=(Users) session.getAttribute("userInfo");
            house.setUserId(users.getId());
            //设置图片
            house.setPath(filename);
            houseService.addHouse(house);
            return "redirect:getHouse";  //跳转到管理页
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("info","上传文件失败..");
        }
        return "redirect:getHouse";
    }
    //查询用户出租房带分页
    @RequestMapping("/getHouse")
    public String getHouse(Page page, HttpSession session, Model model){  //page只为接收页码
        //获取用户id
        Users user=(Users)session.getAttribute("userInfo");
        //调用业务查询出租房
        PageInfo<House> pageInfo=houseService.getHouseByUsers(page,user.getId());
        //将结果填充至MODEL
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }
    //显示单条出租房信息
    @RequestMapping("/getSingleHouse")
    public String getSingleHouse(String id,Model model){
        //获取出租房信息
        House house=houseService.getHouse(id);
        //将信息填充到作用域
        model.addAttribute("h",house);
        System.out.println(house);
        return "showHouse";
    }
    @RequestMapping("/updateHouse")
    public String updateHouse(String delimage,House house,@RequestParam("pfile") CommonsMultipartFile pfile){
            try {
                String oldName = pfile.getOriginalFilename();
                if (oldName.equals("")){
                    houseService.updateHouse(house);
                }else {
                    String path="d:\\images\\";  //存放文件的位置
                    //生成唯一文件名
                    String expname=oldName.substring(oldName.lastIndexOf("."));
                    String filename=System.currentTimeMillis()+expname;
                    File file=new File(path+filename);
                pfile.transferTo(file);
              house.setPath(filename);
              houseService.updateHouse(house);
              File delfile=new File(path+delimage);
              delfile.delete();
                }
                return "redirect:getHouse";
            } catch (IOException e) {
                e.printStackTrace();
            }
        return "login";
    }
    //删除
    @RequestMapping("/delHouse")
    @ResponseBody
    public String delHouse(String id){
        int i = houseService.delHouse(id);
        return "{\"result\":"+i+"}";
    }
    //显示单条出租房信息
    @RequestMapping("/getHouseById")
    public String getHouseById(String id,Model model){
        //获取出租房信息
        House house=houseService.getHouse(id);
        //将信息填充到作用域
        model.addAttribute("house",house);
        return "details";
    }
    //显示单条出租房信息
    @RequestMapping("/searchHouse")
    public String searchHouse(HouseCondition condition, Model model){
        //获取出租房信息
        PageInfo<House> pageInfo = houseService.getHouseBySearch(condition);
        //将信息填充到作用域
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",condition);
        return "list";
    }
}
