package com.cz.model.query;


import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("表列表查询类")
@Data
public class TableListQuery implements Serializable {

    private static final long serialVersionUID = -4034701304173357427L;
    @Query(fieldName = "tableName",type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "表名称")
    private String name;


}
