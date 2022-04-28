package ${package}.entity;

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
import ${package}.model.bo.${className}BO;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
<#if hasLocalDate>
import java.time.LocalDate;
</#if>
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if isNotNullColumns??>
import javax.validation.constraints.*;
</#if>

/**
* @description ${tableName}实体类-${apiAlias}
* @author ${author}
* @date ${date}
**/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${tableName}")
@ApiModel(value = "${tableName}实体类", description = "${apiAlias}")
public class ${className} implements Serializable {

    private static final long serialVersionUID = ${serializableNum}L;

<#if columns??>
    <#list columns as column>

    <#if column.columnKey = 'PRI'>
    @TableId<#if auto>(value = "${column.columnName}", type = IdType.AUTO)</#if>
    </#if>
    <#if column.istNotNull && column.columnKey != 'PRI'>
        <#if column.columnType = 'String'>
    @NotBlank(message = "${column.remark}不能为空")
        <#else>
    @NotNull(message = "${column.remark}不能为空")
        </#if>
    </#if>
    <#if column.changeColumnName = 'createTime' || column.changeColumnName = 'createBy'>
    @TableField(fill = FieldFill.INSERT)
    </#if>
   <#if column.changeColumnName = 'updateTime' || column.changeColumnName = 'updateBy'>
    @TableField(fill = FieldFill.UPDATE)
    </#if>
    <#if column.remark != ''>
    @ApiModelProperty(value = "${column.remark}")
    <#else>
    @ApiModelProperty(value = "${column.changeColumnName}")
    </#if>
    private ${column.columnType} ${column.changeColumnName};
    </#list>

    public ${className}(${className}BO bo){
        BeanUtils.copyProperties(bo, this);
    }
</#if>


}