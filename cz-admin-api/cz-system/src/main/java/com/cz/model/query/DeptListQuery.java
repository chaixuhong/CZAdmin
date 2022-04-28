package com.cz.model.query;


import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("部门列表查询类")
@Data
public class DeptListQuery implements Serializable {

    private static final long serialVersionUID = -3613538562841331349L;

    @Query(fieldName = "name",type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "部门名称")
    private String name;

    @Query(fieldName = "pid",type = Query.Type.EQUAL)
    @ApiModelProperty(value = "部门父级id")
    private Integer pid;

}
