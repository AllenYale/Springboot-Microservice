package com.travischenn.commonweb.repository;

import com.travischenn.commonweb.domain.DO.rbac.RoleUserRelation;

import java.util.List;

public interface RoleUserRelationMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleUserRelation record);

    int insertSelective(RoleUserRelation record);

    RoleUserRelation selectByPrimaryKey(Integer id);

    List<Integer> selectByUserId(Integer id);

    int updateByPrimaryKeySelective(RoleUserRelation record);

    int updateByPrimaryKey(RoleUserRelation record);
}