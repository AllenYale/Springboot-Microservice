package com.travischenn.commonweb.repository;

import com.travischenn.commonweb.domain.DO.rbac.Department;

import java.util.List;

public interface DepartmentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAll();

}