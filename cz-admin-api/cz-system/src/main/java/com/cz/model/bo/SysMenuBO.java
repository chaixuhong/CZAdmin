package com.cz.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author chaizi
 * @description sys_menu操作类-菜单
 * @date 2022-04-21
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sys_menu操作类", description = "菜单")
public class SysMenuBO implements Serializable {

    private static final long serialVersionUID = 1650535278265814L;

    @ApiModelProperty(value = "ID")
    private Integer menuId;
    @ApiModelProperty(value = "上级菜单ID")
    private Integer pid;
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
    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;
    @ApiModelProperty(value = "权限")
    private String permission;
    @ApiModelProperty(value = "菜单类型,0:菜单1：路由2：按钮")
    private Integer menuType;
    @ApiModelProperty(value = "缓存")
    private Boolean isCache;
    @ApiModelProperty(value = "是否有子菜单")
    private Boolean hasChildren;
}