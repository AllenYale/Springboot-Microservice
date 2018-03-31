package com.travischenn.commonweb.web.service.impl.rbac;

import com.travischenn.commonweb.domain.DO.rbac.Department;
import com.travischenn.commonweb.domain.VO.common.CascaderItemBean;
import com.travischenn.commonweb.domain.VO.common.PageBean;
import com.travischenn.commonweb.domain.VO.common.SearchBean;
import com.travischenn.commonweb.repository.DepartmentMapper;
import com.travischenn.commonweb.web.service.interfaces.algorithm.Cascader;
import com.travischenn.commonweb.web.service.interfaces.rbac.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService, Cascader<Department> {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Integer save(Department obj, HttpServletRequest request) {
        return null;
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Integer updateByPrimaryKey(Department obj) {
        return null;
    }

    @Override
    public Department selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public PageBean<List<Department>> selectAll(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public List<Department> selectAll() {
        return null;
    }

    @Override
    public PageBean<List<Department>> selectByKeyWord(SearchBean searchBean) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<CascaderItemBean> converToCascaderItemBeanList(List<Department> beans) {
        return beans.stream().map(department -> {
            CascaderItemBean cascaderItemBean = new CascaderItemBean();
            cascaderItemBean.setLabel(department.getName());
            cascaderItemBean.setValue(String.valueOf(department.getId()));
            cascaderItemBean.setParentId(department.getParentId());
            cascaderItemBean.setCascaderItemBeans(new ArrayList<>());
            return cascaderItemBean;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer getTopId(List<CascaderItemBean> cascaderItemBeans) {

        final int[] topId = {0};

        Map<Integer,Integer> idContainer = new HashMap<>();
        cascaderItemBeans.forEach(cascaderItemBean -> {
            int key = cascaderItemBean.getParentId();
            if(!idContainer.containsKey(key)){
                idContainer.put(key, 1);
            }else{
                idContainer.put(key, idContainer.get(key)+1);
            }
        });

        for (Map.Entry<Integer, Integer> entry : idContainer.entrySet()) {
            int key = entry.getKey();
            Integer value = idContainer.get(key);
            AtomicBoolean isExist = new AtomicBoolean(false);
            if(value == 1){
                cascaderItemBeans.forEach(cascaderItemBean -> {
                    if(StringUtils.equals(cascaderItemBean.getValue(), String.valueOf(key))){
                        isExist.set(true);
                    }
                });
                if(!isExist.get()){
                    topId[0] = key;
                }
            }
        }

        return topId[0];
    }

    @Override
    public List<CascaderItemBean> toCascader(List<CascaderItemBean> cascaderItemBeans, int topId) {
        List<CascaderItemBean> cascaderItemBeansInner = new ArrayList<>();
        List<CascaderItemBean> cascaderItemBeansTemp;
        for (int i = 0; i < cascaderItemBeans.size(); i++) {
            if (cascaderItemBeans.get(i).getParentId() == topId) {
                CascaderItemBean cascaderItemBean = cascaderItemBeans.get(i);
                cascaderItemBeansTemp = toCascader(cascaderItemBeans, Integer.parseInt(cascaderItemBeans.get(i).getValue()));
                if (cascaderItemBeansTemp.size() > 0) {
                    cascaderItemBean.setCascaderItemBeans(cascaderItemBeansTemp);
                }
                cascaderItemBeansInner.add(cascaderItemBean);
            }
        }
        return cascaderItemBeansInner;
    }

    @Override
    public List<CascaderItemBean> toCascader() {
        List<CascaderItemBean> cascaderItemBeans = converToCascaderItemBeanList(departmentMapper.selectAll());
        Integer topId = getTopId(cascaderItemBeans);
        return toCascader(cascaderItemBeans, topId);
    }
}
