package com.cz.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("部门接收类")
@Data
public class SysDeptBO implements Serializable {

    private static final long serialVersionUID = 8562965185348182436L;
    @ApiModelProperty(value = "deptId")
    private Integer deptId;

    @ApiModelProperty(value = "上级部门")
    private Integer pid;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "部门排序不能为空")
    private Integer deptSort;
}
