package com.cz.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author chaixuhong
 * @apiNote 数据返回标准化输出类
 * @date 2019-08-12
 */
@ApiModel(value = "返回类")
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 3068837394742385883L;

    @ApiModelProperty(value = "错误码,默认1是成功,0是失败;以及其它错误码",example = "1")
    private Integer code;
    @ApiModelProperty("提示信息")
    private String errMsg;
    @ApiModelProperty("数据")
    private T data;
}
