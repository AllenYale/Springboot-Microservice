package com.travischenn.commonweb.web.service.impl.rbac;

import com.travischenn.commonweb.domain.DO.rbac.Role;
import com.travischenn.commonweb.domain.DO.rbac.User;
import com.travischenn.commonweb.domain.DTO.rbac.UserDeptTable;
import com.travischenn.commonweb.domain.VO.common.PageBean;
import com.travischenn.commonweb.domain.VO.common.SearchBean;
import com.travischenn.commonweb.domain.VO.user.UserDetail;
import com.travischenn.commonweb.enums.ResultEnum;
import com.travischenn.commonweb.enums.rbac.Gender;
import com.travischenn.commonweb.repository.DepartmentMapper;
import com.travischenn.commonweb.repository.RoleMapper;
import com.travischenn.commonweb.repository.RoleUserRelationMapper;
import com.travischenn.commonweb.repository.UserMapper;
import com.travischenn.commonweb.web.service.impl.authentication.JwtAuthenticationServer;
import com.travischenn.commonweb.web.service.interfaces.rbac.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleUserRelationMapper roleUserRelationMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer save(User user, HttpServletRequest request) {

        User isExist = userMapper.selectByUsername(user.getUsername());
        if(isExist!=null){
            throw new RuntimeException("用户名："+user.getUsername()+" 对应的用户已经存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setOperator(JwtAuthenticationServer.getCurrentUsername(request));
        user.setOperateTime(new Date());
        user.setOperateIp(request.getRemoteHost());
        user.setIsEnabled(1);
        user.setIsAccountExpired(0);
        user.setIsCredentialsExpired(0);
        user.setIsAccountLocked(0);

        return userMapper.insert(user);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public Integer updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<List<User>> selectAll(int pageIndex, int pageSize) {
        return new PageBean<>(ResultEnum.SUCCESS , count() , userMapper.selectPagable(pageIndex * pageSize, pageSize));
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageBean<List<User>> selectByKeyWord(SearchBean searchBean) {
        List<User> users = userMapper.selectByKeyWord(searchBean.getField(), searchBean.getKeyWord());
        return new PageBean<>(ResultEnum.SUCCESS , users.size(), users);
    }

    @Override
    public int count() {
        return userMapper.count();
    }

    @Override
    public UserDetail getUserInfo(String username) {

        UserDetail userDetail = new UserDetail();

        User user = userMapper.selectByUsername(username);
        if(user == null){
            throw new RuntimeException("系统中无:" + username + "对应的用户");
        }

        List<Integer> roleIdList = roleUserRelationMapper.selectByUserId(user.getId());
        if(roleIdList.size() == 0){
            throw new RuntimeException("用户 ID:" + user.getId() + "对应的权限ID不存在");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer roleId : roleIdList) {
            Role sysRole = roleMapper.selectByPrimaryKey(roleId);
            if (sysRole == null) {
                throw new RuntimeException("权限 ID:" + roleId + "对应的权限不存在");
            }
            stringBuilder.append(sysRole.getName()).append(",");
        }
        int length = stringBuilder.toString().length();
        stringBuilder.delete(length -1 , length);

        userDetail.setUser(user);
        userDetail.setRoles(stringBuilder.toString());

        return userDetail;
    }

    @Override
    public PageBean<List<UserDeptTable>> page(String pageIndex, String pageSize) {
        List<UserDeptTable> users = convert(selectAll(Integer.parseInt(pageIndex), Integer.parseInt(pageSize) ).getData());
        return new PageBean<>(ResultEnum.SUCCESS, count(), users);
    }

    @Override
    public PageBean<List<UserDeptTable>> search(SearchBean searchBean) {
        List<UserDeptTable> users = convert(selectByKeyWord(searchBean).getData());
        return new PageBean<>(ResultEnum.SUCCESS, users.size(), users);
    }

    private List<UserDeptTable> convert(List<User> users){
        return users.stream().map(user -> {
            UserDeptTable userDeptTable = new UserDeptTable();
            BeanUtils.copyProperties(user , userDeptTable);
            userDeptTable.setGenderName(Gender.findByCode(String.valueOf(user.getGender())).getDescribe());
            userDeptTable.setDepartmentName(departmentMapper.selectByPrimaryKey(user.getDeptId()).getName());
            return userDeptTable;
        }).collect(Collectors.toList());
    }

}
