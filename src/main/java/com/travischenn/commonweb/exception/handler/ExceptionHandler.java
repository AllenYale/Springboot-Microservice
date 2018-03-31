package com.travischenn.commonweb.exception.handler;

import com.travischenn.commonweb.domain.VO.common.ResultBean;
import com.travischenn.commonweb.enums.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean<String> handle(Exception exception) {
        return new ResultBean<>(ResultEnum.FAILED , exception.getMessage());
    }

}