package com.travischenn.commonweb.domain.VO.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListResultBean extends ResultBean{

    /** 数量 */
    private Integer count;

}
