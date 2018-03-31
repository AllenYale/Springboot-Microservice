package com.travischenn.commonweb.repository;

import com.travischenn.commonweb.domain.DO.rbac.RoleAccessRelation;

public interface RoleAccessRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAccessRelation record);

    int insertSelective(RoleAccessRelation record);

    RoleAccessRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAccessRelation record);

    int updateByPrimaryKey(RoleAccessRelation record);
}