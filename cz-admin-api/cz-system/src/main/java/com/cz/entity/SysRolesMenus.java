package com.cz.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author chaizi
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_roles_menus")
@ApiModel(value = "SysRolesMenus实体类", description = "角色菜单关联")
public class SysRolesMenus implements Serializable {

    private static final long serialVersionUID = 2661213510192190544L;

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;


}