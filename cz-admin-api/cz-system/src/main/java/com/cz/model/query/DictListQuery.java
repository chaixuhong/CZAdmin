package com.cz.model.query;


import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("字典列表查询类")
@Data
public class DictListQuery implements Serializable {

    private static final long serialVersionUID = 7627935797316909077L;

    @Query(blurry = "name,description", type = Query.Type.BLURRY)
    @ApiModelProperty(value = "字典名称、描述")
    private String blurry;
}
