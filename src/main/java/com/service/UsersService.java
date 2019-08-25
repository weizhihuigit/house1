package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.House;
import com.pojo.Users;
import com.util.HouseCondition;
import com.util.UserConditioin;

public interface UsersService {
    //分页查询
    PageInfo<Users> getUsersByPage(UserConditioin userConditioin);
    //添加
    int addDialog(Users type);
    //修改-1 查询单条
    Users getUsers(Integer id);
    //修改-2 修改
    int updateUsers(Users type);
    //删除单条
    int delUsers(Integer id);
    //批量删除
    int delMoreUsers(Integer[] ids);
    //注册
    int regUser(Users users);
    //检查用户名是否存在
    int isUserNameExists(String name);
    //登陆
    Users login(String username,String password);

}
