package com.travischenn.commonweb.repository;

import com.travischenn.commonweb.domain.DO.rbac.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUsername(String username);

    List<User> selectAll();

    List<User> selectPagable(@Param("start") int start , @Param("size") int size);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int count();

    List<User> selectByKeyWord(@Param("field") String field, @Param("keyword") String keyword);

}