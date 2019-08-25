package com.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.DistrictMapper;
import com.mapper.StreetMapper;
import com.pojo.District;
import com.pojo.DistrictExample;
import com.service.ServiceDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceDistrictImpl implements ServiceDistrict {
    @Autowired
private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;


    @Override
    public PageInfo<District> getDistrictByPage(int page, int rows) {
        PageHelper.startPage(page,rows);  //传页码，页大小
        //2.查询所有信息   只是当前页的结果
        List<District> list=districtMapper.selectByExample(null);
        //3.获取分页相关的信息
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }
//添加
    @Override
    public int addDialog(District district) {
        return districtMapper.insertSelective(district);
    }
//修改-1了查询单条
    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }
//修改
    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional
    public int delDistrict(Integer id) {
        //try {
            //删除区域的同时，删除街道   先删除外键，再删除主键
            //1.删除区域下的街道     DELETE FROM street WHERE district_id=id
            streetMapper.delStreetByDistrict(id);
            //2.删除区域
            districtMapper.deleteByPrimaryKey(id);
      //  }catch (Exception e){
           // return -1;
       // }
        return 1;
    }
   //批量删除
    @Override
    public int delMoreDistrict(Integer[] ids) {
        return districtMapper.delMoreDistrict(ids);
    }

    @Override
    public List<District> getDistrictByPage() {
        return districtMapper.selectByExample(null);
    }
}
