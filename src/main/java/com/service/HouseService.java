package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.House;
import com.util.HouseCondition;
import com.util.Page;

import java.util.List;

public interface HouseService {
    //添加  （房屋信息）
    public int addHouse(House house);
    //查询全部租房信息带分页
    public PageInfo<House> getHouseByUsers(Page page, Integer usersid);
    //查询全部出租房信息
    public House getHouse(String id);
    //修改
    public int updateHouse(House house);
    //删除
    public int delHouse(String id);
    //查询审核
    public PageInfo<House>getHouseByIsPass(Page page,Integer state);

    public int PassHouse(String id,Integer state);
    //查询所有
    PageInfo<House>getHouseBySearch(HouseCondition condition);
}
