package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.TypeMapper;
import com.pojo.Type;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo<Type> getTypeByPage(int page, int rows) {
        PageHelper.startPage(page,rows);  //传页码，页大小
        //2.查询所有信息   只是当前页的结果
        List<Type> list=typeMapper.selectByExample(null);
        //3.获取分页相关的信息
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }
    //添加
    @Override
    public int addDialog(Type type) {
        return typeMapper.insertSelective(type);
    }
    //修改-1了查询单条
    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }
    //修改
    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    @Transactional
    public int delType(Integer id) {
        try {
            //删除区域的同时，删除街道   先删除外键，再删除主键
            //1.删除区域下的街道     DELETE FROM street WHERE type_id=id
           // streetMapper.delStreetByType(id);
            //2.删除区域
            typeMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }
    //批量删除
    @Override
    public int delMoreType(Integer[] ids) {
        return typeMapper.delMoreType(ids);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(null);
    }
}
