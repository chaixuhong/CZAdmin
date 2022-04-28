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
 * @since 2021-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_dept")
@ApiModel(value = "SysRoleDept实体类", description = "")
public class SysRoleDept implements Serializable {

    private static final long serialVersionUID = -6726609300667887170L;

    private Integer deptId;

    private Integer roleId;


}