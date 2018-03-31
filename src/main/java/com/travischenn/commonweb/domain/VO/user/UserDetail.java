package com.travischenn.commonweb.domain.VO.user;

import com.travischenn.commonweb.domain.DO.rbac.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private User user;

    private String roles;

    private String department;

}
