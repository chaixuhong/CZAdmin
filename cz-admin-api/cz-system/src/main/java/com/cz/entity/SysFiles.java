package com.cz.entity;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* @description sys_files实体类-文件存储
* @author chaizi
* @date 2022-04-22
**/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_files")
@ApiModel(value = "sys_files实体类", description = "文件存储")
public class SysFiles implements Serializable {

    private static final long serialVersionUID = 1650594824091786L;


    @TableId(value = "file_id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer fileId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件原名称")
    private String fileOriginalName;

    @ApiModelProperty(value = "文件路径")
    private String fileUrl;

    @ApiModelProperty(value = "压缩图地址")
    private String fileUrlThumb;

    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建日期")
    private LocalDate createDate;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "应用名称")
    private String appCode;

    @ApiModelProperty(value = "输入路径")
    private String fileInputPath;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "操作人")
    private String createBy;



}