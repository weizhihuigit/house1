package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.HouseMapper;
import com.pojo.House;
import com.service.HouseService;
import com.util.HouseCondition;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
     private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }
//查询带分页
    @Override
    public PageInfo<House> getHouseByUsers(Page page, Integer usersid) {
        //给page的rows属性设置默认值:后期可能不传页大小
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list=houseMapper.getHouseByUsers(usersid);
        return new PageInfo<House>(list);
    }
//查询
    @Override
    public House getHouse(String id) {
        return houseMapper.getHouseById(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id) {
        House house = new House();
        house.setId(id);
        house.setIsdel(new Integer(1));

        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByIsPass(Page page, Integer state) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list = houseMapper.getHouseByIsPass(state);
        return new PageInfo<House>(list);
    }

    @Override
    public int PassHouse(String id, Integer state) {
        House house=new House();
        //设置id
        house.setId(id);
        //设置审核状态
        house.setIspass(state);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseBySearch(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> list = houseMapper.getHouseBySearch(condition);
        return new PageInfo<>(list);
    }
}
