package com.cz.model.query;


import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("岗位列表查询类")
@Data
public class JobListQuery implements Serializable {

    private static final long serialVersionUID = 7155791544630452083L;
    @Query(fieldName = "name",type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "岗位名称")
    private String name;


}
