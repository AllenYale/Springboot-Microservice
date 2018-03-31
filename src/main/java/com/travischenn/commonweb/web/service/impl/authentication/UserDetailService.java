package com.travischenn.commonweb.web.service.impl.authentication;

import com.travischenn.commonweb.domain.DO.rbac.Role;
import com.travischenn.commonweb.domain.DO.rbac.User;
import com.travischenn.commonweb.exception.exception.RoleNotFountException;
import com.travischenn.commonweb.repository.RoleMapper;
import com.travischenn.commonweb.repository.RoleUserRelationMapper;
import com.travischenn.commonweb.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleUserRelationMapper roleUserRelationMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        User User = userMapper.selectByUsername(username);

        if(User == null){
            throw new UsernameNotFoundException("系统中无:" + username + "对应的用户");
        }

        List<Integer> roleIdList = roleUserRelationMapper.selectByUserId(User.getId());
        if(roleIdList.size() == 0){
            throw new RoleNotFountException("用户 ID:" + User.getId() + "对应的权限ID不存在");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer roleId : roleIdList) {
            Role sysRole = roleMapper.selectByPrimaryKey(roleId);
            if (sysRole == null) {
                throw new RoleNotFountException("权限 ID:" + roleId + "对应的权限不存在");
            }
            stringBuilder.append(sysRole.getName()).append(",");
        }
        int length = stringBuilder.toString().length();
        stringBuilder.delete(length -1 , length);

        return new org.springframework.security.core.userdetails.User(username,
                User.getPassword(),
                numTobool(User.getIsEnabled()),
                !numTobool(User.getIsAccountExpired()),
                true,
                !numTobool(User.getIsAccountLocked()),
                AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString()));

    }

    private boolean numTobool(Integer num){
        return num == 1;
    }

}
