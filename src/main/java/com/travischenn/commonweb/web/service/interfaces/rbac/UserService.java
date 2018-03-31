package com.travischenn.commonweb.web.service.interfaces.rbac;

import com.travischenn.commonweb.domain.DO.rbac.User;
import com.travischenn.commonweb.domain.DTO.rbac.UserDeptTable;
import com.travischenn.commonweb.domain.VO.common.PageBean;
import com.travischenn.commonweb.domain.VO.common.SearchBean;
import com.travischenn.commonweb.domain.VO.user.UserDetail;

import java.util.List;

public interface UserService extends CommonService<User> {

    /**
     * 通过用户名获取用户详情
     * @param username 用户名
     * @return 用户详情
     */
    UserDetail getUserInfo(String username);

    /**
     * 分页返回用户列表
     * @param pageIndex 页码
     * @param pageSize  页幅
     * @return 用户表格对象列表
     */
    PageBean<List<UserDeptTable>> page(String pageIndex , String pageSize);

    /**
     * 根据关键词搜索用户列表
     * @param searchBean 搜索对象
     * @return 搜索结果对应的用户表格对象列表
     */
    PageBean<List<UserDeptTable>> search(SearchBean searchBean);

}
