package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UsersMapper;
import com.pojo.House;
import com.pojo.Users;
import com.pojo.UsersExample;
import com.service.UsersService;
import com.util.HouseCondition;
import com.util.MD5Utils;
import com.util.UserConditioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUsersByPage(UserConditioin userConditioin) {
        PageHelper.startPage(userConditioin.getPage(),userConditioin.getRows());  //传页码，页大小
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));
        //判断名称
        if(userConditioin.getName()!=null){
            criteria.andNameLike("%"+userConditioin.getName()+"%");
        }
        //判断电话
        if(userConditioin.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userConditioin.getTelephone()+"%");
        }
        //2.查询所有信息   只是当前页的结果
        List<Users> list=usersMapper.selectByExample(usersExample);
        //3.获取分页相关的信息
        PageInfo<Users> pageInfo=new PageInfo(list);
        return pageInfo;
    }
    //添加
    @Override
    public int addDialog(Users users) {
        return usersMapper.insertSelective(users);
    }
    //修改-1了查询单条
    @Override
    public Users getUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
    //修改
    @Override
    public int updateUsers(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    @Transactional
    public int delUsers(Integer id) {
        try {
            //删除区域的同时，删除街道   先删除外键，再删除主键
            //1.删除区域下的街道     DELETE FROM street WHERE users_id=id
           // streetMapper.delStreetByUsers(id);
            //2.删除区域
            usersMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }
    //批量删除
    @Override
    public int delMoreUsers(Integer[] ids) {
        return usersMapper.delMoreUsers(ids);
    }
//注册
    @Override
    public int regUser(Users users) {
        users.setIsadmin(new Integer("0"));
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }
//检查用户名是否存在
    @Override
    public int isUserNameExists(String name) {
        return usersMapper.getUserCount(name);
    }
//登陆
    @Override
    public Users login(String username, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(usersExample);
        if(list.size()==0)
            return null;
        else
            return list.get(0);
    }


}
