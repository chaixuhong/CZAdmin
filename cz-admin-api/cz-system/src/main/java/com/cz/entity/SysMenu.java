package com.cz.entity;
import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import com.cz.model.bo.SysMenuBO;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author chaizi
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu实体类", description = "系统菜单")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1143643296692752534L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "上级菜单ID")
    @NotNull(message = "父级id不能为空")
    private Integer pid;

    @ApiModelProperty(value = "菜单类型,0:菜单1：路由2：按钮")
    private Integer menuType;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "组件名称")
    private String name;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "排序")
    private Integer menuSort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String path;

    @ApiModelProperty(value = "是否外链")
    private Boolean iFrame;

    @ApiModelProperty(value = "缓存")
    private Boolean isCache;

    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "是否有子菜单")
    private Boolean hasChildren;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    public SysMenu(SysMenuBO bo){
        BeanUtils.copyProperties(bo, this);
    }
}