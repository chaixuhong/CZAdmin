package com.cz.model.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
@ApiModel("角色接收类")
@Data
public class SysRoleBO implements Serializable {

    private static final long serialVersionUID = 7650940241957418990L;

    @ApiModelProperty(value = "ID")
    private Integer roleId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "角色级别")
    @NotNull(message = "角色级别不能为空")
    private Integer level;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    @ApiModelProperty(value = "自定义部门")
    private List<Integer> depts;
}

