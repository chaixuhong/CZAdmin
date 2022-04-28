package ${package}.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
<#if hasLocalDate>
import java.time.LocalDate;
</#if>
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if isNotNullColumns??>
import javax.validation.constraints.*;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* @description ${tableName}操作类-${apiAlias}
* @author ${author}
* @date ${date}
**/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "${tableName}操作类", description = "${apiAlias}")
public class ${className}BO implements Serializable {

    private static final long serialVersionUID = ${serializableNumBO}L;

<#if columns??>
    <#list columns as column>
      <#if column.changeColumnName != 'createTime' && column.changeColumnName != 'updateTime' && column.changeColumnName != 'createBy' && column.changeColumnName != 'updateBy' >
        <#if column.istNotNull>
            <#if column.columnType = 'String'>
        @NotBlank(message = "${column.remark}不能为空")
            <#else>
        @NotNull(message = "${column.remark}不能为空")
            </#if>
        </#if>
        <#if column.remark != ''>
        @ApiModelProperty(value = "${column.remark}")
        <#else>
        @ApiModelProperty(value = "${column.changeColumnName}")
        </#if>
        private ${column.columnType} ${column.changeColumnName};
      </#if>
    </#list>
</#if>
}