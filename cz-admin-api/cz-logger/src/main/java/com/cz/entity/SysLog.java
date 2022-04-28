package com.cz.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author chaizi
 * @since 2021-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value = "SysLog实体类", description = "系统日志")
@NoArgsConstructor
public class SysLog implements Serializable {

    private static final long serialVersionUID = 243688164260809564L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "方法名")
    private String method;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "日志类型")
    private String logType;

    @ApiModelProperty(value = "请求ip")
    private String requestIp;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "请求耗时")
    private Long time;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "结果")
    private String result;

    @ApiModelProperty(value = "异常详细")
    private String exceptionDetail;

    @ApiModelProperty(value = "创建日期")
    private LocalDate createDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    public SysLog(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}