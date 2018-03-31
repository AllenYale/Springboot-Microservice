package com.travischenn.commonweb.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class RoleNotFountException extends AuthenticationException {

    public RoleNotFountException(String msg) {
        super(msg);
    }

}
