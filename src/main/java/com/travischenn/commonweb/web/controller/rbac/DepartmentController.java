package com.travischenn.commonweb.web.controller.rbac;

import com.travischenn.commonweb.domain.VO.common.CascaderItemBean;
import com.travischenn.commonweb.web.service.interfaces.rbac.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @RequestMapping("/cascader")
    public List<CascaderItemBean> structure(){
        return departmentService.toCascader();
    }

}
