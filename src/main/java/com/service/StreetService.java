package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.Street;
import com.util.Page;

import java.util.List;


public interface StreetService {
    //分页查询
    PageInfo<Street> getStreetByDistrict(Integer did, Page page);
    //添加
    int addDialog(Street street);
    //修改-1 查询单条
    Street getStreet(Integer id);
    //修改-2 修改
    int updateStreet(Street street);
    //删除单条
    int delStreet(Integer id);
    //批量删除
    int delMoreStreet(Integer[] ids);
    //查询
    List<Street> getStreetByDistrict(Integer did);
}
