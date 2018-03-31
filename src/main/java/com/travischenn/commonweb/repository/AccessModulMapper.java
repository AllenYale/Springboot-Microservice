package com.travischenn.commonweb.repository;

import com.travischenn.commonweb.domain.DO.rbac.AccessModul;

public interface AccessModulMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccessModul record);

    int insertSelective(AccessModul record);

    AccessModul selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccessModul record);

    int updateByPrimaryKey(AccessModul record);
}