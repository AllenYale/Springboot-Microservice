package com.travischenn.commonweb.web.service.interfaces.algorithm;

import com.travischenn.commonweb.domain.VO.common.CascaderItemBean;

import java.util.List;

public interface Cascader<T> {

    /**
     * 将 T 对象列表转化为 CascaderItemBean 对象列表
     * @param beans T 对象列表
     * @return CascaderItemBean 对象列表
     */
    List<CascaderItemBean> converToCascaderItemBeanList(List<T> beans);

    /**
     * 获取 CascaderItemBean 对象列表中的最顶级元素
     * @param cascaderItemBeans CascaderItemBean对象列表
     * @return 顶级元素 ID
     */
    Integer getTopId(List<CascaderItemBean> cascaderItemBeans);

    /**
     * 将 CascaderItemBean 对象列表 转化成 Cascader结构
     * @param cascaderItemBeans CascaderItemBean 对象列表
     * @param topId             树形结构最顶层 ID
     * @return                  CascaderBean 对象列表
     */
    List<CascaderItemBean> toCascader(List<CascaderItemBean> cascaderItemBeans, int topId);

}
