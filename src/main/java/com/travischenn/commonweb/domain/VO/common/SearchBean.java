package com.travischenn.commonweb.domain.VO.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBean {

    /** 字段 */
    private String field;

    /** 关键词 */
    private String keyWord;

}
