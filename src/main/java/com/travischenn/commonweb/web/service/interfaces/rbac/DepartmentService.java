package com.travischenn.commonweb.web.service.interfaces.rbac;

import com.travischenn.commonweb.domain.DO.rbac.Department;
import com.travischenn.commonweb.domain.VO.common.CascaderItemBean;

import java.util.List;

public interface DepartmentService extends CommonService<Department> {
    List<CascaderItemBean> toCascader();
}
