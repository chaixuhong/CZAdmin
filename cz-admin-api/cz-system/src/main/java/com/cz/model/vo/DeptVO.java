package com.cz.model.vo;

import com.cz.entity.SysDept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("部门输出类")
@Data
public class DeptVO extends SysDept {

    private List<DeptVO> children;

}
