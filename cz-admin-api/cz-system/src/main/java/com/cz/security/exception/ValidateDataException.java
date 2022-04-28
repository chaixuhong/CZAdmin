package com.cz.security.exception;

import com.cz.enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;
/**
 * 验证码异常
 *
 * @author chaizi
 * @date 2021年05月12日
 **/
public class ValidateDataException extends AuthenticationException {

    private ResultEnum resultEnum;

    public ValidateDataException(ResultEnum resultEnum) {
        super(resultEnum.getErrMsg());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
