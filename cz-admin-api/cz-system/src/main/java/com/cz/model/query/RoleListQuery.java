package com.cz.model.query;

import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("角色列表查询类")
@Data
public class RoleListQuery implements Serializable {

    private static final long serialVersionUID = 669014981787117846L;

    @Query(fieldName = "createTime", type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "日期范围")
    private String dataRange;


    @Query(blurry = "name,description", type = Query.Type.BLURRY)
    @ApiModelProperty(value = "名称、描述")
    private String blurry;

}
