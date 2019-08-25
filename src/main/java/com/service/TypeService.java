package com.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.pojo.Type;

import java.util.List;

public interface TypeService {
    //分页查询
    PageInfo<Type> getTypeByPage(int page, int rows);
    //添加
    int addDialog(Type type);
    //修改-1 查询单条
    Type getType(Integer id);
    //修改-2 修改
    int updateType(Type type);
    //删除单条
    int delType(Integer id);
    //批量删除
    int delMoreType(Integer[] ids);
    //查询所有
    List<Type> getAllType();
}
