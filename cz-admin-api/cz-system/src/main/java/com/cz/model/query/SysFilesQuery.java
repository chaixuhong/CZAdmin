package com.cz.model.query;

import com.cz.annotation.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
* @author chaizi
* @date 2022-04-22
**/
@Data
public class SysFilesQuery{

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "时间区间")
    private String createTime;
}