package com.travischenn.commonweb.repository;

import com.travischenn.commonweb.domain.DO.rbac.AccessControl;

public interface AccessControlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccessControl record);

    int insertSelective(AccessControl record);

    AccessControl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccessControl record);

    int updateByPrimaryKey(AccessControl record);
}