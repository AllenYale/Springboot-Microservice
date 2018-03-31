package com.travischenn.commonweb.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travischenn.commonweb.domain.VO.common.ResultBean;
import com.travischenn.commonweb.enums.ResultEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("loginOutSuccessHandler")
public class LoginOutSuccessHandler implements LogoutSuccessHandler {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new ResultBean<>(ResultEnum.SUCCESS,"登出成功")));
    }

}
