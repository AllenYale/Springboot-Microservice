package com.travischenn.commonweb.domain.VO.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CascaderItemBean {

    /** 字面值 */
    private String label;

    /** 实际值 */
    private String value;

    /** 上级 ID */
    private int parentId;

    /** 子级联 */
    private List<CascaderItemBean> cascaderItemBeans;

}
