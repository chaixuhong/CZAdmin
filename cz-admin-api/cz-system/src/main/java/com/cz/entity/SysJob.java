package com.cz.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author chaizi
 * @since 2022-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_job")
@ApiModel(value = "SysJob实体类", description = "岗位")
public class SysJob implements Serializable {

    private static final long serialVersionUID = -3338753142843601301L;
    @ApiModelProperty(value = "ID")
    @TableId(value = "job_id", type = IdType.AUTO)
    private Integer jobId;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer jobSort;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}