package com.travischenn.commonweb.domain.VO.common;

import com.travischenn.commonweb.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageBean<T> extends ResultBean<T>{
    private int count;

    public PageBean(ResultEnum resultEnum, int count, T data) {
        super(resultEnum, data);
        this.count = count;
    }

}