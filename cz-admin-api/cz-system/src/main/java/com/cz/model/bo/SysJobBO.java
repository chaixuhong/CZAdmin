package com.cz.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("岗位接收类")
@Data
public class SysJobBO implements Serializable {

    private static final long serialVersionUID = 1017144556865839195L;

    @ApiModelProperty(value = "jobId")
    private Integer jobId;

    @ApiModelProperty(value = "岗位名称")
    @NotBlank(message = "岗位名称不能为空")
    private String name;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer jobSort;


}
