package com.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.pojo.District;

import java.util.List;

public interface ServiceDistrict {
    //分页查询
    PageInfo<District> getDistrictByPage(int page, int rows);
    //添加
    int addDialog(District district);
    //修改-1 查询单条
    District getDistrict(Integer id);
    //修改-2 修改
    int updateDistrict(District district);
    //删除单条
    int delDistrict(Integer id);
    //批量删除
    int delMoreDistrict(Integer[] ids);
    //查询
    List<District> getDistrictByPage();
}
