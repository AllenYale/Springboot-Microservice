package com.travischenn.commonweb.web.service.interfaces.rbac;

import com.travischenn.commonweb.domain.VO.common.PageBean;
import com.travischenn.commonweb.domain.VO.common.SearchBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommonService<T> {

    /**
     * 新增
     * @param obj 新增对象
     * @return 新增成功的对象
     */
     Integer save(T obj, HttpServletRequest request);

    /**
     * 通过 ID 删除对象
     * @param id 被删除对象 ID
     */
     Integer deleteByPrimaryKey(Integer id);

    /**
     * 删除全部对象
     */
    void deleteAll();

    /**
     * 更新对象
     * @param obj 更新对象
     * @return 更新完成对象
     */
     Integer updateByPrimaryKey(T obj);

    /**
     * 通过 ID 查找对象
     * @param id ID
     * @return 查找到的对象
     */
     T selectByPrimaryKey(Integer id);

    /**
     * 分页查找
     * @param pageIndex 页码
     * @param pageSize 页面尺寸
     *
     * @return 结果集合
     */
     PageBean<List<T>> selectAll(int pageIndex , int pageSize);

    /**
     * 获取表格中所有数据
     * @return 对象列表
     */
    List<T> selectAll();

    /**
     * 根据关键字进行搜索
     * @param searchBean 搜索对象
     * @return 搜索对象结果列表
     */
    PageBean<List<T>> selectByKeyWord(SearchBean searchBean);

    /**
     * 获取表格中所有数据的数量
     * @return 总数据条数
     */
    int count();

}
