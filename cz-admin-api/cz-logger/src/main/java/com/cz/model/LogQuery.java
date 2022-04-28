package com.cz.model;

import com.cz.annotation.Logger;
import com.cz.annotation.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ApiModel("日志查询类")
@Data
public class LogQuery {

    @Query(blurry = "username,description,address,requestIp,method,params")
    @ApiModelProperty(value = "模糊查询")
    private String blurry;

    @Query
    @ApiModelProperty(value = "日志类型")
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    @ApiModelProperty(value = "时间区间")
    private String createTime;

}
