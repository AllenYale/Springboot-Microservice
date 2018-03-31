package com.travischenn.commonweb.domain.DTO.rbac;

import com.travischenn.commonweb.domain.DO.rbac.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeptTable extends User{

    String departmentName;

    String genderName;

}
