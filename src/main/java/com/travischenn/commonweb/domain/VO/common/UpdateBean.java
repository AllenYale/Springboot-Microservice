package com.travischenn.commonweb.domain.VO.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBean {

    /** 对象ID */
    private int id;

    /** 要更新的字段 */
    @NotBlank(message = "更新字段不能为空")
    private String field;

    /** 更新字段对应的值 */
    private String value;

}
