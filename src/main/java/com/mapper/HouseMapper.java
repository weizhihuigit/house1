package com.mapper;

import com.pojo.House;
import com.pojo.HouseExample;
import java.util.List;


import com.util.HouseCondition;
import org.apache.ibatis.annotations.Param;

public interface HouseMapper {
    long countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //查询用户发布的出租房
    public List<House> getHouseByUsers(Integer userid);
    //查询出租房信息
    public House getHouseById(String id);
    //查询审核
    public List<House> getHouseByIsPass(Integer ispass);
    //查询所有出租房信息
    public List<House>getHouseBySearch(HouseCondition condition);
}