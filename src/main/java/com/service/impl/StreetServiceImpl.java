package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.StreetMapper;
import com.pojo.Street;
import com.pojo.StreetExample;
import com.service.StreetService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StreetServiceImpl implements StreetService { 
    @Autowired
private StreetMapper streetMapper;
    @Override
    public PageInfo<Street> getStreetByDistrict(Integer did, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        //创建查询条件
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did); //封装查询

        List<Street> list=  streetMapper.selectByExample(streetExample);
        return new PageInfo<Street>(list);
    }

    @Override
    public int addDialog(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public Street getStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStreet(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }

    @Override
    public int delStreet(Integer id) {
        try {
            //删除区域的同时，删除街道   先删除外键，再删除主键
            //1.删除区域下的街道     DELETE FROM street WHERE street_id=id
            // streetMapper.delStreetByStreet(id);
            //2.删除区域
            streetMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            return -1;
        }
        return 1;
    
    }

    @Override
    public int delMoreStreet(Integer[] ids) {
        return streetMapper.delMoreStreet(ids);
    }

    @Override
    public List<Street> getStreetByDistrict(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did); //封装查询
        List<Street> list = streetMapper.selectByExample(streetExample);
        return list;
    }
}
