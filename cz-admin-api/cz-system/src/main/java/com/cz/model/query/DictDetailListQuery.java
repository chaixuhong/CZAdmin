package com.cz.model.query;


import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("字典列表查询类")
@Data
public class DictDetailListQuery implements Serializable {

    private static final long serialVersionUID = 7627935797316909077L;

    @ApiModelProperty(value = "父级id")

    @Query
    @NotNull(message = "dictId不能为空")
    private Integer dictId;

    @Query(type = Query.Type.RIGHT_LIKE)
    @ApiModelProperty(value = "字典名称、描述")
    private String label;
}
