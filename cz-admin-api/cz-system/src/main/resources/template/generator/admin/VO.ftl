package ${package}.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cz.entity.${className};
import java.util.List;
/**
* @description ${tableName}输出类-${apiAlias}
* @author ${author}
* @date ${date}
**/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "${tableName}实体类", description = "${apiAlias}")
public class ${className}VO implements Serializable {

    private static final long serialVersionUID = ${serializableNumVO}L;

<#if columns??>
    <#list columns as column>
        <#if column.remark != ''>
        @ApiModelProperty(value = "${column.remark}")
        <#else>
        @ApiModelProperty(value = "${column.changeColumnName}")
        </#if>
        private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>

    /**
     * 实体类转换为输出类
     *
     * @param resource
     */
    public ${className}VO(${className} resource){
        BeanUtils.copyProperties(resource,this);
    }

    /**
     * 实体类集合转换为输出类集合
     *
     * @param resource
     */
    public static List<${className}VO> transform(List<${className}> resource){
        return JSONArray.parseArray(JSONObject.toJSONString(resource),${className}VO.class);
    }
}