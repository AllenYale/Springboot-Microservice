package com.travischenn.commonweb.domain.DO.rbac;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private Integer deptId;

    private String name;

    private Integer gender;

    private String mobile;

    private String email;

    private Integer isEnabled;

    private Integer isAccountExpired;

    private Integer isCredentialsExpired;

    private Integer isAccountLocked;

    private String remark;

    private String operator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    private String operateIp;

}