package com.travischenn.commonweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {

    SUCCESS("100001" , "成功"),

    WARN("100002" ,"警告"),

    FAILED("100003" ,"失败"),

    UNAUTHORIZED("100004" ,"访问权限被拒绝");

    /**
     * 字段对应的编号
     */
    private String code;

    /**
     * 字段描述
     */
    private String describe;

}
