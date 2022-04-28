package com.cz.model.query;

import com.cz.annotation.DataScope;
import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户列表查询类")
@Data
@DataScope
public class UserListQuery implements Serializable {

    private static final long serialVersionUID = 2230151710398980753L;

    @ApiModelProperty(value = "部门路径")
    @Query(fieldName = "dept.path", type = Query.Type.INNER_LIKE)
    private String deptPath;

    @Query(blurry = "username,phone", type = Query.Type.BLURRY)
    @ApiModelProperty(value = "手机号、账号")
    private String blurry;

    @Query(fieldName = "createTime", type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "日期范围")
    private String dataRange;

    @Query(fieldName = "enabled")
    @ApiModelProperty(value = "状态")
    private Boolean enabled;
}
