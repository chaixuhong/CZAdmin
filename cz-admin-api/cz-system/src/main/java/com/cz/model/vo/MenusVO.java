package com.cz.model.vo;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("菜单包装输出类")
@Data
public class MenusVO {
    @ApiModelProperty(value = "ID")
    private Integer menuId;

    @ApiModelProperty(value = "上级菜单ID")
    private Integer pid;

    @ApiModelProperty(value = "菜单类型")
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

    @ApiModelProperty(value = "是否有子菜单")
    private Boolean hasChildren;

    @ApiModelProperty(value = "权限")
    private String permission;

    private List<MenusVO> children = Lists.newArrayList();

}
