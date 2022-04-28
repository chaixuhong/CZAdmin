package com.cz.exception;

import com.cz.enums.ResultEnum;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {

    private int code;

    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getErrMsg());
        this.code = resultEnum.getCode();
    }

    public GlobalException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
