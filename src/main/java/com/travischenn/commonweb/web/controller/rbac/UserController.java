package com.travischenn.commonweb.web.controller.rbac;

import com.travischenn.commonweb.domain.DO.rbac.User;
import com.travischenn.commonweb.domain.DTO.rbac.UserDeptTable;
import com.travischenn.commonweb.domain.VO.common.PageBean;
import com.travischenn.commonweb.domain.VO.common.ResultBean;
import com.travischenn.commonweb.domain.VO.common.SearchBean;
import com.travischenn.commonweb.domain.VO.user.UserDetail;
import com.travischenn.commonweb.enums.ResultEnum;
import com.travischenn.commonweb.web.service.impl.authentication.JwtAuthenticationServer;
import com.travischenn.commonweb.web.service.interfaces.rbac.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping("/info")
    public ResultBean<UserDetail> info(HttpServletRequest request){
        String currentUsername = JwtAuthenticationServer.getCurrentUsername(request);
        return new ResultBean<>(ResultEnum.SUCCESS , userService.getUserInfo(currentUsername));
    }

    @GetMapping
    @RequestMapping("/page")
    public PageBean<List<UserDeptTable>> page(@RequestParam("pageIndex") int pageIndex , @RequestParam("pageSize") int pageSize){
        return userService.page(String.valueOf(pageIndex) , String.valueOf(pageSize));
    }

    @GetMapping
    @RequestMapping("/search")
    public PageBean<List<UserDeptTable>> search(@RequestParam("field") String field , @RequestParam("keyword") String keyword){
        return userService.search(new SearchBean(field , keyword));
    }

    @GetMapping
    @RequestMapping("/save")
    public ResultBean<String> save(@RequestParam("username") String username ,
                        @RequestParam("password") String password ,
                        @RequestParam("deptId") String deptId ,
                        @RequestParam("name") String name ,
                        @RequestParam("gender") String gender ,
                        @RequestParam("mobile") String mobile ,
                        @RequestParam("email") String email ,
                        @RequestParam("remark") String remark,
                        HttpServletRequest request){

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setDeptId(Integer.valueOf(deptId));
        user.setName(name);
        user.setGender(Integer.valueOf(gender));
        user.setMobile(mobile);
        user.setEmail(email);
        user.setRemark(remark);

        if(userService.save(user, request) == 1){
            return new ResultBean<>(ResultEnum.SUCCESS , "");
        }else{
            throw new RuntimeException("增加失败");
        }

    }

}
