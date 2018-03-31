package com.travischenn.commonweb.enums.rbac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Gender {

    SECRET("0" ,"保密"),

    MALE("1" , "男"),

    FEMALE("2" ,"女");

    /** 字段编号 */
    private String code;

    /** 字段描述 */
    private String describe;

    public static Gender findByCode(String code){
        switch (code){
            case "0": return SECRET;
            case "1": return MALE;
            case "2": return FEMALE;
            default: throw new RuntimeException("性别编号: "+code+"对应的性别不存在");
        }
    }

}
