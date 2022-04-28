package com.cz.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("字典接收类")
@Data
public class SysDictBO implements Serializable {

    private static final long serialVersionUID = 1876109116519346246L;
    @ApiModelProperty(value = "dictId")
    private Integer dictId;

    @ApiModelProperty(value = "排序")
    private Integer dictSort;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "值")
    private String description;
}
