package ${package}.model.query;

import lombok.Data;
<#if hasLocalDate>
import java.time.LocalDate;
</#if>
<#if hasLocalDateTime>
import java.time.LocalDateTime;
</#if>
<#if queryHasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if betweens??>
import java.util.List;
</#if>
<#if queryColumns??>
import com.cz.annotation.Query;
</#if>

/**
* @author ${author}
* @date ${date}
**/
@Data
public class ${className}Query{
<#if queryColumns??>
    <#list queryColumns as column>

<#if column.queryType = '='>
    /** 精确 */
    @Query
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = 'Like'>
    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = '!='>
    /** 不等于 */
    @Query(type = Query.Type.NOT_EQUAL)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = 'NotNull'>
    /** 不为空 */
    @Query(type = Query.Type.NOT_NULL)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = '>='>
    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = '<='>
    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    private ${column.columnType} ${column.changeColumnName};
</#if>
    </#list>
</#if>
<#if betweens??>
    <#list betweens as column>
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private String ${column.changeColumnName};
    </#list>
</#if>
}