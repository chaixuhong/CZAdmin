package com.cz.exception;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;

@Data
public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(String msg) {
        super(msg);
        this.code = HttpServletResponse.SC_BAD_REQUEST;
    }
}
